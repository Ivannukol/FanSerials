package com.example.ivan.fanserial.data.serials;

import com.example.ivan.fanserial.model.Genres;
import com.example.ivan.fanserial.model.GetGenresResponse;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetGenres {
    public ArrayList<Genres> genres = new ArrayList<>();
    Genrest viwe;

    SerialModel serialModel;

    public GetGenres(Genrest genrest) {
        viwe = genrest;
        serialModel = new SerialModel();
    }


    public void getGenrest() {
        serialModel.genres.reposSerialsGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::cha
                        , Throwable::printStackTrace);

    }

    private void cha(GetGenresResponse getGenresResponse) {
        viwe.setGenres(getGenresResponse);
    }


}
