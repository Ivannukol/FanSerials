package com.example.ivan.fanserial;

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
import com.example.ivan.fanserial.adapter.SerialsAdapter;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ClientFanSerial clientStarWars;
    private Retrofit retrofitSerials;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitSerials = new Retrofit.Builder()
                .baseUrl(MovieDB.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        clientStarWars = retrofitSerials.create(ClientFanSerial.class);
     /*   clientStarWars.reposSerials(60735)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::serials
                        , Throwable::printStackTrace);
*/

        clientStarWars.reposSerialsPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::serials
                        , Throwable::printStackTrace);

    }

    private void serials(PopularSerials popularSerials) {
        Log.d("MyLog",  ""+ popularSerials.getPage());
        RecyclerView rv = findViewById(R.id.rvSerials);

        rv.setLayoutManager(new LinearLayoutManager(this));
        SerialsPopularAdapter adapter = new SerialsPopularAdapter();

        adapter.setData(popularSerials.getResults());
        rv.setAdapter(adapter);


    }

/*
    private void serials(Serials serials) {
        tvInfo.setText(serials.getName());
       // ImageView   dotMenu=findViewById(R.id.dotMenu);

        //  registerForContextMenu(dotMenu);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + serials.getSeasons().get(serials.getSeasons().size() - 1).getPoster_path())
                .override(400, 200)
                .fitCenter()
                .into(imPosted);

        RecyclerView rv = findViewById(R.id.rvSerials);

        rv.setLayoutManager(new LinearLayoutManager(this));
        SerialsAdapter adapter = new SerialsAdapter();
        List<Serials> l = new ArrayList<>();
        l.add(serials);
        l.add(serials);
        l.add(serials);
        l.add(serials);
        l.add(serials);
        l.add(serials);
        l.add(serials);
        l.add(serials);
        l.add(serials);
        l.add(serials);
        adapter.setData(l);
        rv.setAdapter(adapter);


    }*/

}