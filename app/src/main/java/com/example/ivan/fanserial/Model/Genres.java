package com.example.ivan.fanserial.model;

/**
 * Created by Ivan on 07.03.2018.
 */

public class Genres {
    private String name;
    private int id;
    private int size = 0;

    public String getName() {
        return name;
    }

    public Genres(int id) {
        this.id = id;
    }

    public Genres() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
 /*       size += name.length();
        if (size > 15){
            size=0;
            return "\n" + name;}*/
        return name + " ";
    }
}
