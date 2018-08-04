package com.example.ivan.fanserial.ui.main;

import com.example.ivan.fanserial.data.serials.NewSeriesModel;
import com.example.ivan.fanserial.data.serials.SerialModel;
import com.example.ivan.fanserial.fragment.ListSerialsFragment;
import com.example.ivan.fanserial.helper.dao.DaoSerials;
import com.example.ivan.fanserial.helper.model.SerialsDB;
import com.example.ivan.fanserial.model.Serials;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListSerialsPresenter {
    ListSerialsFragment listSerialsFragment;
    NewSeriesModel newSeriesModel;
    ArrayList<SerialsDB> serialsDB;

    public ListSerialsPresenter(ListSerialsFragment listSerialsFragment) {
        this.listSerialsFragment = listSerialsFragment;
        newSeriesModel = new NewSeriesModel();
    }

    public void setDelete(int id) {
        DaoSerials daoSerials = new DaoSerials();
        daoSerials.delete(id);
    }

    public void getDateSeries() {
        getDbSerial();
        serialsDB.size();

        io.reactivex.Observable.fromIterable(serialsDB).flatMap(i -> newSeriesModel.serials.reposSerials(i.get_idSerials()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getSerial
                        , Throwable::printStackTrace);
    }

    private void getSerial(Serials serials) {
        listSerialsFragment.setListSerials(serials);
    }

    public void getDbSerial() {
        serialsDB = new ArrayList<>();
        DaoSerials daoSerials = new DaoSerials();
        serialsDB.addAll(daoSerials.select());
    }


}
