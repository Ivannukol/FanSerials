package com.example.ivan.fanserial.ui.main;


import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;

import com.example.ivan.fanserial.data.serials.NewSeriesModel;
import com.example.ivan.fanserial.helper.SerialsHelper;
import com.example.ivan.fanserial.helper.dao.DaoSerials;
import com.example.ivan.fanserial.helper.dao.DaoSeries;
import com.example.ivan.fanserial.helper.model.SerialsDB;
import com.example.ivan.fanserial.helper.model.SeriesDB;
import com.example.ivan.fanserial.model.Result;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewSerialPresenter {
    private NewSerialViwe viwe;
    private NewSeriesModel newSeriesModel;
    ArrayList<SerialsDB> serialsDB;
    ArrayList<SeriesDB> seriesDB;
    SerialsHelper serialsHelper;
    private SQLiteDatabase database;

    public NewSerialPresenter(NewSerialViwe viwe, LayoutInflater s) {
        this.viwe = viwe;
        newSeriesModel = new NewSeriesModel();

    }


    public void getDetalisSeasons() {
        getDbSerial();
        io.reactivex.Observable.fromIterable(serialsDB).flatMap(i -> newSeriesModel.serials.reposSerials(i.get_idSerials()))
                .subscribeOn(Schedulers.io())
                .flatMap(seriasl -> io.reactivex.Observable.range(1, seriasl.getNumber_of_seasons())
                        .subscribeOn(Schedulers.io()).flatMap(t -> (
                                newSeriesModel.detalisSesons.reposDetalisSeasons(seriasl.getId(), t))), (seriasl, genres) -> {
                    genres.setOriginal_name(seriasl.getName());
                    return genres;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getSerial
                        , Throwable::printStackTrace);

    }
    List<Result> results=new ArrayList<>();

    private void getSerial(Result result1) {

        results.add(result1);
        getDbSerie();

        for (int i = 0; i < results.size(); i++) {
            for (int id = 0; id < seriesDB.size(); id++) {
                for (int j = 0; j < results.get(i).getEpisodes().size(); j++) {
                    if (results.get(i).getEpisodes().get(j).getId() == seriesDB.get(id).get_idSerials()
                            &&results.get(i).getEpisodes().get(j).getEpisode_number() == seriesDB.get(id).getSeriesNumber()
                            &&results.get(i).getEpisodes().get(j).getSeason_number() == seriesDB.get(id).getSeasonNumber()) {
                        results.get(i).getEpisodes().remove(j);
                    }
                }
            }
            if (results.get(i).getEpisodes().size() == 0) {
                results.remove(i);
                i = -1;

            }
        }
        viwe.setEpisod(results);
    }




/*    io.reactivex.Observable.fromIterable(id).flatMap(i -> newSeriesModel.serials.reposSerials(i))
                .subscribeOn(Schedulers.io())
                .flatMap(seriasl -> io.reactivex.Observable.range(1,/*seriasl.getNumber_of_seasons()3).flatMap(t -> (
            newSeriesModel.detalisSesons.reposDetalisSeasons(seriasl.getId(), t))), (seriasl, genres) -> {
        genres.setOriginal_name(seriasl.getName());
        return genres;
    }).toList()
                .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::getSerial
            , Throwable::printStackTrace);*/

    private void getSerial(List<Result> results) {
        getDbSerie();

        for (int i = 0; i < results.size(); i++) {
            for (int id = 0; id < seriesDB.size(); id++) {
                for (int j = 0; j < results.get(i).getEpisodes().size(); j++) {
                    if (results.get(i).getEpisodes().get(j).getId() == seriesDB.get(id).get_idSerials()
                            &&results.get(i).getEpisodes().get(j).getEpisode_number() == seriesDB.get(id).getSeriesNumber()
                            &&results.get(i).getEpisodes().get(j).getSeason_number() == seriesDB.get(id).getSeasonNumber()) {
                        results.get(i).getEpisodes().remove(j);
                    }
                }
            }
            if (results.get(i).getEpisodes().size() == 0) {
                results.remove(i);
                i = -1;

            }
        }
        viwe.setEpisod(results);

    }


    public void getDbSerial() {
        serialsDB = new ArrayList<>();
        DaoSerials daoSerials = new DaoSerials();
        serialsDB.addAll(daoSerials.select());
    }


    public void getDbSerie() {
        seriesDB = new ArrayList<>();
        DaoSeries daoSeries = new DaoSeries();
        seriesDB.addAll(daoSeries.select());

    }
}
