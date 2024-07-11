package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class PlaylistService {
    private final Logger logger = LoggerFactory.getLogger(PlaylistService.class.getName());
    private final DeezerSongSearchService deezerSongSearchService;
    private final DeezerArtistSearchService deezerArtistSearchService;
    private final TasteDiveService tasteDiveService;
    private final Counter calledforServiceCounter;
    private final Timer serviceExecutionTimer;
    private final AzureOpenAiChatModel chatModel;

    @Autowired
    public PlaylistService(
            DeezerSongSearchService deezerSongSearchService,
            DeezerArtistSearchService deezerArtistSearchService,
            TasteDiveService tasteDiveService,
            AzureOpenAiChatModel chatModel,
            MeterRegistry metricsRegistry) {
        this.deezerSongSearchService = deezerSongSearchService;
        this.deezerArtistSearchService = deezerArtistSearchService;
        this.tasteDiveService = tasteDiveService;
        this.calledforServiceCounter = metricsRegistry.counter("called_for_service_counter");
        this.serviceExecutionTimer = metricsRegistry.timer("service_execution_timer");
        this.chatModel = chatModel;
    }

    public String getPlaylist(String artistName) throws JsonProcessingException {
        // add logging
        final long startTime = System.currentTimeMillis();

        // create a new empty playlist
        PlaylistResponse playlistResponse = new PlaylistResponse();

        // get a list of similar artists
        List<String> artistNames = this.tasteDiveService.getSimilarArtists(artistName);

        // get top song for each artist id
        artistNames.forEach(name -> {
            // get artist's id by name
            int artistId = this.deezerArtistSearchService.getArtistId(name);

            // print current artist id
            logger.info("ArtistId {} name: {}", artistId, name);

            // get top song for current artist
            DeezerSongSearchResultData topSong = this.deezerSongSearchService.getSongData(artistId);

            // only process song data if song data exists
            if (topSong != null) {
                // create the response item object
                PlaylistSong song = new PlaylistSong();
                song.songId = topSong.id();
                song.songName = topSong.title();
                song.songUrl = topSong.link();
                song.artistName = name;

                // push the object to the playlist array
                playlistResponse.songs.add(song);
            }
        });

        // get playlist name and description from chatgpt
        String instructions = """
        I will give you an object representing a deezerplaylist in the next format:
        { songs: { artistName, songId, songName, songUrl } } 
        
        Please take just the artistName and songName and create a name for this playlist and 2-3 sentence long description.
        Append the playlist name and description to my initial object next to songs property.  
        
        Send back just the JSON without anything else. Also remove the ```json and ``` at the end. I need a pure JSON.
        """;

        ObjectMapper mapper = new ObjectMapper();
        String playlistString =  mapper.writeValueAsString(playlistResponse);

        String prompt = instructions + playlistString;

        System.out.println("Sending prompt to chatGPT API");
        System.out.println(prompt);

        String chatgptResponse = this.chatModel.call(
                new Prompt(
                        prompt,
                        AzureOpenAiChatOptions
                                .builder()
                                .build()
                )
        ).getResult().getOutput().getContent();

        // logs
        calledforServiceCounter.increment();
        serviceExecutionTimer.record(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);

        logger.info("Created playlist in {} seconds", System.currentTimeMillis() - startTime);

        return chatgptResponse;
    }
}
