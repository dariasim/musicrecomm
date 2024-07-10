package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    public PlaylistService(
            DeezerSongSearchService deezerSongSearchService,
            DeezerArtistSearchService deezerArtistSearchService,
            TasteDiveService tasteDiveService,
            MeterRegistry metricsRegistry) {
        this.deezerSongSearchService = deezerSongSearchService;
        this.deezerArtistSearchService = deezerArtistSearchService;
        this.tasteDiveService = tasteDiveService;
        this.calledforServiceCounter = metricsRegistry.counter("called_for_service_counter");
        this.serviceExecutionTimer = metricsRegistry.timer("service_execution_timer");
    }

    public PlaylistResponse getPlaylist(String artistName) {
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
                PlaylistResponseItem playlistResponseItem = new PlaylistResponseItem();
                playlistResponseItem.songId = topSong.id();
                playlistResponseItem.songName = topSong.title();
                playlistResponseItem.songUrl = topSong.link();
                playlistResponseItem.artistName = name;

                // push the object to the playlist array
                playlistResponse.data.add(playlistResponseItem);
            }
        });

    //logs
        calledforServiceCounter.increment();
        serviceExecutionTimer.record(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);

        logger.info("Created playlist in {} seconds", System.currentTimeMillis() - startTime);

        return playlistResponse;
    }
}
