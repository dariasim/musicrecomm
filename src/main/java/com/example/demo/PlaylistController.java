package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Playlist API", description = "API for searching for the most popular song of the artist")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final AzureOpenAiChatModel azureOpenAiChatModel;

    @Autowired
    public PlaylistController(PlaylistService playlistService, AzureOpenAiChatModel azureOpenAiChatModel) {
        this.playlistService = playlistService;
        this.azureOpenAiChatModel = azureOpenAiChatModel;
    }

    @Operation(summary = "Get a playlist", description = "Fetches most popular song by the artist")
    @GetMapping("/playlist/{artistName}")
    public String getPlaylist(@PathVariable String artistName) throws JsonProcessingException {
    return playlistService.getPlaylist(artistName);
    }


    //@Operation(summary = "Get a playlist with description", description = "Generated the playlist with songs of similar artists")
    //@GetMapping("/playlist-data/{artistName}")
    //public PlaylistResponse getPlaylist(@PathVariable String artistName) {
    //    return playlistService.getPlaylist(artistName);
    //}


}
