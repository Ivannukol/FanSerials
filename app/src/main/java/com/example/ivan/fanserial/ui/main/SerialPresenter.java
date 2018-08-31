package com.example.ivan.fanserial.ui.main;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.view.View;

import com.example.ivan.fanserial.About;
import com.example.ivan.fanserial.FanSerialApplication;
import com.example.ivan.fanserial.helper.dao.DaoSerials;
import com.example.ivan.fanserial.model.Genres;
import com.example.ivan.fanserial.model.PopularSerials;
import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.data.serials.SerialModel;
import com.example.ivan.fanserial.helper.SerialsHelper;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Ivan on 18.03.2018.
 */

public class SerialPresenter {
    SerialView view;
    SerialModel serialModel;


    public SerialPresenter(SerialView view) {
        this.view = view;
        serialModel = new SerialModel();
    }

    public void addToFavoriteSerial(Result item) {
        DaoSerials daoSerials = new DaoSerials();
        daoSerials.add(item.getId(), item.getName(), 0);
    }

    public void onAboutSerial(Result item, View v) {
        Intent in = new Intent(v.getContext(), About.class);
        in.putExtra("result", item);
       v.getContext().startActivity(in);
    }

    public void getPopularSerials(int page) {
        serialModel.clientFanSerialPopulation.reposSerialsPopular(page)
                .subscribeOn(Schedulers.io())
                .flatMap(seriasl -> serialModel.genres.reposSerialsGenres()
                                .map(t -> t.getGenres())
                                .flatMapIterable(t -> t)
                                .sorted((o1, o2) -> o1.getId()
                                        .compareTo(o2.getId())).toList().toObservable()
                        , (seriasl, genres) -> {
                            for (Result i : seriasl.getResults()) {
                                ArrayList<Genres> serialGenres = new ArrayList<>();
                                for (int id : i.getGenre_ids()) {
                                    int index = Collections.binarySearch(genres, new Genres(id), (o1, o2) -> o1.getId().compareTo(o2.getId()));
                                    if (index >= 0) {
                                        serialGenres.add(genres.get(index));
                                    }
                                }
                                i.setGenres(serialGenres);
                            }
                            return seriasl;

                        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t->{view.setSerials(t.getResults());}
                        , Throwable::printStackTrace);
    }

    private void serialsP(PopularSerials popularSerials) {
        view.setSerials(popularSerials.getResults());
    }


}
