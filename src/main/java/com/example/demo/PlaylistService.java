package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlaylistService {

    private final DeezerSongSearchService deezerSongSearchService;
    private final DeezerArtistSearchService deezerArtistSearchService;
    private final TasteDiveService tasteDiveService;

    public PlaylistService(
            DeezerSongSearchService deezerSongSearchService,
            DeezerArtistSearchService deezerArtistSearchService,
            TasteDiveService tasteDiveService) {
        this.deezerSongSearchService = deezerSongSearchService;
        this.deezerArtistSearchService = deezerArtistSearchService;
        this.tasteDiveService = tasteDiveService;
    }

    public PlaylistResponse getPlaylist(String artistName) {

        // create a new empty playlist
        PlaylistResponse playlistResponse = new PlaylistResponse();

        // get a list of similar artists
        List<String> artistNames = this.tasteDiveService.getSimilarArtists(artistName);

        // get top song for each artist id
        artistNames.forEach(name -> {
            // get artist's id by name
            int artistId = this.deezerArtistSearchService.getArtistId(name);

            // print current artist id
            System.out.println(artistId);

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

        return playlistResponse;
    }
}
