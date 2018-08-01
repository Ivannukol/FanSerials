package com.example.ivan.fanserial.Model;

import java.util.ArrayList;

/**
 * Created by Ivan on 06.03.2018.
 */

public class Serials {
    private String name;
    private ArrayList<Seasons> seasons;
    private String overview;
    private Double popularity;
    private String poster_path;
    private String first_air_date;
    private Genres[] genres;
    private int number_of_episodes;
    private int number_of_seasons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeasons(ArrayList<Seasons> seasons) {
        this.seasons = seasons;
    }

    public ArrayList<Seasons> getSeasons() {
        return seasons;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public Genres[] getGenres() {
        return genres;
    }

    public void setGenres(Genres[] genres) {
        this.genres = genres;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }
}
