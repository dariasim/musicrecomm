package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface DeezerApiArtistSearch {
    @GetExchange("artist?q={name}")
    DeezerArtistSearchResponse getArtistId(@PathVariable String name);
}



