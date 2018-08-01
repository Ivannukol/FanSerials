package com.example.ivan.fanserial.model;

import java.util.ArrayList;

public class GetGenresResponse {
    private ArrayList<Genres> genres;

    public void setGenres(ArrayList<Genres> genres) {
        this.genres = genres;
    }

    public ArrayList<Genres> getGenres() {
        return genres;
    }
}
