package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface TasteDiveApi {
    @GetExchange("?method=artist.getsimilar&artist={name}&api_key=dee03a0c97c1bb2d47109e15be9a13a1&format=json")
    TasteDiveResponse getSimilarArtists(@PathVariable String name);
}
