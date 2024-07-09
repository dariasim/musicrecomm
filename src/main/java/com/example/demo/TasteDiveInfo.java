package com.example.demo;

import java.util.List;

public record TasteDiveInfo (String name, String type) {
}

record TasteDiveSimilar (List<TasteDiveInfo> results) {
}
