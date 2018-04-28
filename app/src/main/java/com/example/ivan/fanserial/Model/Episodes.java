package com.example.ivan.fanserial.model;

/**
 * Created by Ivan on 23.03.2018.
 */

public class Episodes {
    private String air_date;
    private int episode_number;
    private String name;
    private int id;
    private int season_number;
    private String nameSerial;
    private String poster;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getNameSerial() {
        return nameSerial;
    }

    public void setNameSerial(String nameSerial) {
        this.nameSerial = nameSerial;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }
}
