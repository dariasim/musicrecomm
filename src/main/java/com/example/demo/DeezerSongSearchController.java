package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Deezer Pop Song Search API", description = "API for searching for the most popular song of the artist")
public class DeezerSongSearchController {

    private final DeezerSongSearchService deezerSongSearchService;

    @Autowired
    public DeezerSongSearchController(DeezerSongSearchService deezerSongSearchService) {
        this.deezerSongSearchService = deezerSongSearchService;
    }

    @Operation(summary = "Get most popular song by the artist", description = "Fetches most popular song by the artist")
    @GetMapping("/artist-song-data/{artistId}")
    public DeezerSongSearchResultData getSongData(@PathVariable int artistId) {
        return deezerSongSearchService.getSongData(artistId);
    }
}
