package com.example.demo;
import java.util.ArrayList;
import java.util.List;

class PlaylistResponseItem {
    public String artistName;
    public String songName;
    public long songId;
    public String songUrl;
}

public class PlaylistResponse {

    public PlaylistResponse() {
        this.data = new ArrayList<>();
    }

    public List<PlaylistResponseItem> data;
}


