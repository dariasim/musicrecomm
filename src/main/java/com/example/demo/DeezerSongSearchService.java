package com.example.demo;

import org.springframework.stereotype.Service;


@Service
public class DeezerSongSearchService {

    private final DeezerApiSongSearch deezerApiSongSearch;

    public DeezerSongSearchService(DeezerApiSongSearch deezerApiSongSearch) {
        this.deezerApiSongSearch = deezerApiSongSearch;
    }

    public DeezerSongSearchResultData getSongData(int artistId) {
        return this.deezerApiSongSearch.getSongData(artistId).data().getFirst();
    }
}
