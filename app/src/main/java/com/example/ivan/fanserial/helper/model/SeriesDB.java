package com.example.ivan.fanserial.helper.model;

public class SeriesDB {
    private int _idSerials;
    private int _idSeries;
    private int seriesNumber;
    private int seasonNumber;

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int get_idSerials() {
        return _idSerials;
    }

    public void set_idSerials(int _idSerials) {
        this._idSerials = _idSerials;
    }

    public int get_idSeries() {
        return _idSeries;
    }

    public void set_idSeries(int _idSeries) {
        this._idSeries = _idSeries;
    }

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public int getSeeSeries() {
        return seeSeries;
    }

    public void setSeeSeries(int seeSeries) {
        this.seeSeries = seeSeries;
    }

    private int seeSeries;
}
