package com.example.demo;

import java.util.ArrayList;
import java.util.List;

class PlaylistSong {
    public String artistName;
    public String songName;
    public long songId;
    public String songUrl;
}

public class PlaylistResponse {
    public String playlistName;
    public String playlistDescription;
    public List<PlaylistSong> songs;

    public PlaylistResponse() {
        this.songs = new ArrayList<>();
    }
}
