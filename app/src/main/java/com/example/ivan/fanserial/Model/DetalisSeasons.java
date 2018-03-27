package com.example.ivan.fanserial.Model;

import java.util.ArrayList;

/**
 * Created by Ivan on 23.03.2018.
 */

public class DetalisSeasons {
    private int season_number;
    private ArrayList<Episodes> episodes;
    private String _id;
    private int id;

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

    public ArrayList<Episodes> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episodes> episodes) {
        this.episodes = episodes;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
