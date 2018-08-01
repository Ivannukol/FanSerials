package com.example.ivan.fanserial.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Test  implements Serializable {
    private String name;
    private int id;
private ArrayList<Integer> t;

    public ArrayList<Integer> getT() {
        return t;
    }

    public void setT(ArrayList<Integer> t) {
        this.t = t;
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


}
