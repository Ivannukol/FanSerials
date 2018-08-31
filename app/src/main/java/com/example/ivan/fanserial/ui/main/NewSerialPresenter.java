package com.example.ivan.fanserial.ui.main;


import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.ivan.fanserial.data.serials.NewSeriesModel;
import com.example.ivan.fanserial.helper.SerialsHelper;
import com.example.ivan.fanserial.helper.dao.DaoSeasons;
import com.example.ivan.fanserial.helper.dao.DaoSerials;
import com.example.ivan.fanserial.helper.dao.DaoSeries;
import com.example.ivan.fanserial.helper.model.SeasonsDB;
import com.example.ivan.fanserial.helper.model.SerialsDB;
import com.example.ivan.fanserial.helper.model.SeriesDB;
import com.example.ivan.fanserial.model.Episodes;
import com.example.ivan.fanserial.model.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewSerialPresenter {
    private NewSerialViwe viwe;
    private NewSeriesModel newSeriesModel;
    private ArrayList<SerialsDB> serialsDB;
    private ArrayList<SeriesDB> seriesDB;
    private ArrayList<SeasonsDB> seasonsDB;
    private boolean flag = true;

    public NewSerialPresenter(NewSerialViwe viwe) {
        this.viwe = viwe;
        newSeriesModel = new NewSeriesModel();
        getDbSerial();
        getDbSeasons();
    }


    public void getDetalisSeasons(int i) {
        if (serialsDB.size() - 1 >= i)

            newSeriesModel.serials.reposSerials(serialsDB.get(i).get_idSerials())
                    .subscribeOn(Schedulers.io())
                    .flatMap(seriasl -> io.reactivex.Observable.range(1, seriasl.getNumber_of_seasons())
                            .subscribeOn(Schedulers.io()).flatMap(t -> (
                                    newSeriesModel.detalisSesons.reposDetalisSeasons(seriasl.getId(), t))), (seriasl, genres) -> {
                        genres.setOriginal_name(seriasl.getName());
                        return genres;
                    }).toList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::getSerial
                            , Throwable::printStackTrace);

/*
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
                        , Throwable::printStackTrace);*/

    }

    private void getSerial(List<Result> results) {

        getDbSerie();
        for (int i = 0; i < results.size(); i++) {
            for (int id = 0; id < seriesDB.size(); id++) {
                for (int j = 0; j < results.get(i).getEpisodes().size(); j++) {

                    if (results.get(i).getEpisodes().get(j).getId() == seriesDB.get(id).get_idSerials()
                            && results.get(i).getEpisodes().get(j).getEpisode_number() == seriesDB.get(id).getSeriesNumber()
                            && results.get(i).getEpisodes().get(j).getSeason_number() == seriesDB.get(id).getSeasonNumber()) {
                        results.get(i).getEpisodes().remove(j);
                    }
                }
            }

            if (results.get(i).getEpisodes().size() == 0) {
                results.remove(i);
                i = -1;

            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date date = format.parse(results.get(i).getEpisodes().get(0).getAir_date());
                    if (System.currentTimeMillis() <= date.getTime()) {
                        results.remove(i);
                        i = -1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        viwe.setEpisod(results, flag);
        flag = false;
    }


    /*
        List<Result> results = new ArrayList<>();

        private void getSerial(Result result) {
            results = new ArrayList<>();
            results.add(result);
            getDbSerie();
            Log.d("ser",result.getName());
            for (int i = 0; i < results.size(); i++) {
                for (int id = 0; id < seriesDB.size(); id++) {
                    for (int j = 0; j < results.get(i).getEpisodes().size(); j++) {
                        if (results.get(i).getEpisodes().get(j).getId() == seriesDB.get(id).get_idSerials()
                                && results.get(i).getEpisodes().get(j).getEpisode_number() == seriesDB.get(id).getSeriesNumber()
                                && results.get(i).getEpisodes().get(j).getSeason_number() == seriesDB.get(id).getSeasonNumber()) {
                            results.get(i).getEpisodes().remove(j);
                        }
                    }
                }

                if (results.get(i).getEpisodes().size() == 0) {
                    results.remove(i);
                    i = -1;

                } else {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        Date date = format.parse(results.get(i).getEpisodes().get(0).getAir_date());
                        if (System.currentTimeMillis() <= date.getTime()) {
                            results.remove(i);
                            i = -1;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            viwe.setEpisod(results);
        }
    */
    public int getDbSerial() {
        serialsDB = new ArrayList<>();
        DaoSerials daoSerials = new DaoSerials();
        serialsDB.addAll(daoSerials.select());
        if (serialsDB.size() == 0)
            return 0;
        return serialsDB.size();
    }

    public void getDbSeasons() {
        seasonsDB = new ArrayList<>();
        DaoSeasons daoSeasons = new DaoSeasons();
        seasonsDB.addAll(daoSeasons.select());
        seasonsDB.size();
    }

    public void getDbSerie() {
        seriesDB = new ArrayList<>();
        DaoSeries daoSeries = new DaoSeries();
        seriesDB.addAll(daoSeries.select());

    }
}
