package com.example.ivan.fanserial.helper.model;

public class SerialsDB {
    private int _idSerials;
    private String name;
    private int seeSerial;

    public int get_idSerials() {
        return _idSerials;
    }

    public void set_idSerials(int _idSerials) {
        this._idSerials = _idSerials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeeSerial() {
        return seeSerial;
    }

    public void setSeeSerial(int seeSerial) {
        this.seeSerial = seeSerial;
    }
}
