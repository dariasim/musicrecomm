package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeezerSongSearchService {

    private final DeezerApiSongSearch deezerApiSongSearch;

    public DeezerSongSearchService(DeezerApiSongSearch deezerApiSongSearch) {
        this.deezerApiSongSearch = deezerApiSongSearch;
    }

    public DeezerSongSearchResultData getSongData(int artistId) {
        List<DeezerSongSearchResultData> data = this.deezerApiSongSearch.getSongData(artistId).data();
        return !data.isEmpty() ? data.getFirst() : null;
    }
}
