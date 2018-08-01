package com.example.ivan.fanserial.ui.main;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.ivan.fanserial.helper.dao.DaoSerials;
import com.example.ivan.fanserial.helper.model.SerialsDB;
import com.example.ivan.fanserial.model.Episodes;
import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.data.serials.NewSeriesModel;
import com.example.ivan.fanserial.helper.SerialsHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MySeriesPresenter {
    private NewSeriesModel newSeriesModel;
    private MySeriesViwe mySeriesViwe;

<<<<<<< HEAD
    ArrayList<SerialsDB> serialsDB;
=======
    ArrayList<SerialsDB>serialsDB;
>>>>>>> origin/dev

    public MySeriesPresenter(MySeriesViwe mySeriesViwe) {
        this.mySeriesViwe = mySeriesViwe;
        newSeriesModel = new NewSeriesModel();
    }

    public void getDateSeries() {
        getDbSerial();


        io.reactivex.Observable.fromIterable(serialsDB).flatMap(i -> newSeriesModel.serials.reposSerials(i.get_idSerials()))
                .subscribeOn(Schedulers.io())
                .flatMap(seriasl ->
                        newSeriesModel.detalisSesons.reposDetalisSeasons(seriasl.getId(), seriasl.getNumber_of_seasons()), (seriasl, genres) -> {
                    genres.setOriginal_name(seriasl.getName());
                    return genres;

                }).toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getSerial
                        , Throwable::printStackTrace);
    }

    private void getSerial(List<Result> results) {
        ArrayList<Episodes> episodes = new ArrayList<>();
        ArrayList<Episodes> newepisodes = new ArrayList<>();

        for (Result s : results)
            for (Episodes t : s.getEpisodes()) {
                t.setName(s.getOriginal_name());
                t.setPoster(s.getPoster_path());
                episodes.add(t);
            }
        for (int i = 0; i < episodes.size(); i++) {
            if (episodes.get(i).getAir_date() == null) {

                break;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date date = format.parse(episodes.get(i).getAir_date());
                if (System.currentTimeMillis() <= date.getTime()) {
                    newepisodes.add(episodes.get(i));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        mySeriesViwe.setMySeries(newepisodes);
    }


<<<<<<< HEAD
    public boolean getDbSerial() {
        serialsDB = new ArrayList<>();
        DaoSerials daoSerials = new DaoSerials();
        serialsDB.addAll(daoSerials.select());
        if (serialsDB.size() == 0)
            return false;
        return true;
=======
    public void getDbSerial() {
        serialsDB= new ArrayList<>();
        DaoSerials daoSerials=new DaoSerials();
       serialsDB.addAll(daoSerials.select());

>>>>>>> origin/dev

    }
}
