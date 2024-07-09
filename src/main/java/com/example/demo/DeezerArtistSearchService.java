package com.example.demo;

import org.springframework.stereotype.Service;


@Service
public class DeezerArtistSearchService {

    private final DeezerApiArtistSearch deezerApiArtistSearch;

    public DeezerArtistSearchService(DeezerApiArtistSearch deezerApiArtistSearch) {
        this.deezerApiArtistSearch = deezerApiArtistSearch;
    }

    public int getArtistId(String artist) {
        return this.deezerApiArtistSearch.getArtistId(artist).data().getFirst().id();
    }
}
