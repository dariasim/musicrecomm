package com.example.demo;
import java.util.List;

public record DeezerSongSearchResponse (List<DeezerSongSearchResultData> data) {
}
