package com.example.ivan.fanserial.Present;

import com.example.ivan.fanserial.InfoSerial;
import com.example.ivan.fanserial.Model.PopularSerials;
import com.example.ivan.fanserial.MovieDB;
import com.example.ivan.fanserial.PresentIntrfes.RetrofiltrContractView;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.example.ivan.fanserial.data.serials.ClientFanSerial;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivan on 13.03.2018.
 */

public class PresentorRetrofiltr {
    private ClientFanSerial clientFanSerialPopulation;
    private Retrofit retrofitSerialsPopulation;
    private ClientFanSerial clientFanSerial;
    private Retrofit retrofitSerials;
    private RetrofiltrContractView view;
    private InfoSerial infoSerial;



    public PresentorRetrofiltr(RetrofiltrContractView view) {
        this.view = view;

        retrofitSerialsPopulation = new Retrofit.Builder()
                .baseUrl(MovieDB.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        clientFanSerialPopulation = retrofitSerialsPopulation.create(ClientFanSerial.class);

        clientFanSerialPopulation.reposSerialsPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::serialsP
                        , Throwable::printStackTrace);


    }




    private void serialsP(PopularSerials popularSerials) {
        SerialsPopularAdapter adapter = new SerialsPopularAdapter(popularSerials.getResults());
        adapter.setData(popularSerials.getResults());
        view.showRecyclerView(adapter);
    }

    public  PresentorRetrofiltr(int id,InfoSerial view) {
        infoSerial = view;
        retrofitSerialsPopulation = new Retrofit.Builder()
                .baseUrl(MovieDB.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        clientFanSerialPopulation = retrofitSerialsPopulation.create(ClientFanSerial.class);

       clientFanSerialPopulation.reposSerials(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t->{infoSerial.showPopular(t);}
                        , Throwable::printStackTrace);
    }


}
