package com.example.ivan.fanserial;

import android.graphics.drawable.Icon;
import android.provider.MediaStore;

import com.example.ivan.fanserial.Model.PopularSerials;
import com.example.ivan.fanserial.Model.Serials;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ivan on 05.03.2018.
 */

public interface ClientFanSerial {
    @GET("/3/tv/{id}?api_key=" + MovieDB.key + "&language=rus")
//https://api.themoviedb.org/3/tv/60735?api_key=c87afad022683444d24949b33d3d0cb6&language=rus
    Observable<Serials> reposSerials(@Path("id") int id);

    // Observable<List<Serials>> reposSerials(@Path("id") int id);

    @GET("/3/tv/popular?api_key=" + MovieDB.key + "&language=ru-ru&page=1")
    Observable<PopularSerials> reposSerialsPopular();


}