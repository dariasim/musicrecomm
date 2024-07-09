package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Playlist API", description = "API for searching for the most popular song of the artist")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Operation(summary = "Get a playlist", description = "Fetches most popular song by the artist")
    @GetMapping("/playlist/{artistName}")
    public PlaylistResponse getPlaylist(@PathVariable String artistName) {
        return playlistService.getPlaylist(artistName);
    }
}
