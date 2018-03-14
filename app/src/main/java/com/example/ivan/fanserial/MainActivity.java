package com.example.ivan.fanserial;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivan.fanserial.Model.PopularSerials;
import com.example.ivan.fanserial.Model.Serials;
import com.example.ivan.fanserial.Present.PresentorRetrofiltr;
import com.example.ivan.fanserial.PresentIntrfes.RetrofiltrContractView;
import com.example.ivan.fanserial.adapter.SerialsAdapter;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.example.ivan.fanserial.helper.SerialsHelper;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RetrofiltrContractView {
       PresentorRetrofiltr presentorRetrofiltr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presentorRetrofiltr= new PresentorRetrofiltr(this);


    }

    @Override
    public void showRecyclerView(SerialsPopularAdapter data) {
        RecyclerView rv = findViewById(R.id.rvSerials);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(data);
    }

}