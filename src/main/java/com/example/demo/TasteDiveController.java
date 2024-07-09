package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "TasteDive API", description = "API for fetching similar artists from TasteDive")
public class TasteDiveController {

    private final TasteDiveService tasteDiveService;

    @Autowired
    public TasteDiveController(TasteDiveService tasteDiveService) {
                this.tasteDiveService = tasteDiveService;
    }

    @Operation(summary = "Get similar artists", description = "Fetches similar artists from TasteDive API based on the given artist name")
    @GetMapping("/similar-artists")
    public List<String> getSimilarArtists(@RequestParam String artist) {
        return tasteDiveService.getSimilarArtists(artist);
    }
}
