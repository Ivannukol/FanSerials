package com.example.ivan.fanserial.data.serials;

import com.example.ivan.fanserial.MovieDB;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewSeriesModel {

    public ClientFanSerial detalisSesons = new Retrofit.Builder()
            .baseUrl(MovieDB.url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ClientFanSerial.class);

    public ClientFanSerial serials = new Retrofit.Builder()
            .baseUrl(MovieDB.url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ClientFanSerial.class);
}
