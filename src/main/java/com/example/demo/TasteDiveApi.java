package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface TasteDiveApi {
    @GetExchange("similar?q={name}&type=music&k=1030873-DariaSim-85E09E21")
    TasteDiveResponse getSimilarArtists(@PathVariable String name);
}
