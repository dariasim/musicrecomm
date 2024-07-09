package com.example.demo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TasteDiveService {

    private final TasteDiveApi tasteDiveApi;

    public TasteDiveService(TasteDiveApi tasteDiveApi) {
        this.tasteDiveApi = tasteDiveApi;
    }

    public List<String> getSimilarArtists(String artist) {
        return this.tasteDiveApi.getSimilarArtists(artist).similar().results().stream().map(it -> it.name()).toList().subList(0, 10);
    }
}
