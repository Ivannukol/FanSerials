package com.example.ivan.fanserial.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 23.03.2018.
 */

public class DetalisSeasons extends ExpandableGroup {
    private int season_number;
    private ArrayList<Episodes> episodes;
    private String _id;
    private int id;
    private String poster_path;



    public DetalisSeasons(String title, List items) {
        super(title, items);
    }

    //private List<Object> b;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
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

/*
    @Override
    public List<Object> getChildObjectList() {
        return b;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        b = list;
    }*/
}
