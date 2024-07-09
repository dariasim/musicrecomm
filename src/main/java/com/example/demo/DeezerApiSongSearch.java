package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface DeezerApiSongSearch {
    @GetExchange("/{artistId}/top")
    DeezerSongSearchResponse getSongData(@PathVariable int artistId);
}



