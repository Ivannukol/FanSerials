package com.example.ivan.fanserial.model;

import java.io.Serializable;

/**
 * Created by Ivan on 07.03.2018.
 */

public class Genres  implements Serializable {
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
         return name + " ";
    }
}
