package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Deezer Artist Search API", description = "API for searching for an artist based on the name")
public class DeezerArtistSearchController {

    private final DeezerArtistSearchService deezerArtistSearchService;

    @Autowired
    public DeezerArtistSearchController(DeezerArtistSearchService deezerArtistSearchService) {
        this.deezerArtistSearchService = deezerArtistSearchService;
    }

    @Operation(summary = "Get similar artists", description = "Fetches similar artists from TasteDive API based on the given artist name")
    @GetMapping("/artist-id")
    public int getArtistId(@RequestParam String artist) {
        return deezerArtistSearchService.getArtistId(artist);
    }
}
