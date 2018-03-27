package com.example.ivan.fanserial.ui.main;

import com.example.ivan.fanserial.Model.PopularSerials;
import com.example.ivan.fanserial.data.serials.SerialModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    public void getPopularSerials() {
        serialModel.clientFanSerialPopulation.reposSerialsPopular(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::serialsP
                        , Throwable::printStackTrace);
    }

    private void serialsP(PopularSerials popularSerials) {
        view.setSerials(popularSerials.getResults());
    }

   /* public SerialPresenter(int id, InfoSerial view) {
        SerialModel serialModel = new SerialModel();
        serialModel.clientFanSerialPopulation.reposSerials(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> {
                            view.showPopular(t);
                        }
                        , Throwable::printStackTrace);
    }*/
}
