package com.example.ivan.fanserial.data.serials;

import com.example.ivan.fanserial.MovieDB;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivan on 18.03.2018.
 */

public class SerialModel {
    public ClientFanSerial clientFanSerialPopulation = new Retrofit.Builder()
            .baseUrl(MovieDB.url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ClientFanSerial.class);


    public ClientFanSerial retrofitSerialsPopulation = new Retrofit.Builder()
            .baseUrl(MovieDB.url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ClientFanSerial.class);
}
