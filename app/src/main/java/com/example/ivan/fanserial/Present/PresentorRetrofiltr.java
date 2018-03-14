package com.example.ivan.fanserial.Present;

import com.example.ivan.fanserial.ClientFanSerial;
import com.example.ivan.fanserial.Model.PopularSerials;
import com.example.ivan.fanserial.MovieDB;
import com.example.ivan.fanserial.PresentIntrfes.RetrofiltrContractView;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivan on 13.03.2018.
 */

public class PresentorRetrofiltr {
    ClientFanSerial clientStarWars;
    private Retrofit retrofitSerials;
    RetrofiltrContractView view;

    public PresentorRetrofiltr(RetrofiltrContractView view) {
        this.view = view;

        retrofitSerials = new Retrofit.Builder()
                .baseUrl(MovieDB.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        clientStarWars = retrofitSerials.create(ClientFanSerial.class);

        clientStarWars.reposSerialsPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::serials
                        , Throwable::printStackTrace);
    }

    private void serials(PopularSerials popularSerials) {
       SerialsPopularAdapter adapter = new SerialsPopularAdapter();
       adapter.setData(popularSerials.getResults());
       view.showRecyclerView(adapter);
    }
}
