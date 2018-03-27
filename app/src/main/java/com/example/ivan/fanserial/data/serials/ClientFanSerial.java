package com.example.ivan.fanserial.data.serials;

import com.example.ivan.fanserial.Model.Genres;
import com.example.ivan.fanserial.Model.PopularSerials;
import com.example.ivan.fanserial.Model.Serials;
import com.example.ivan.fanserial.MovieDB;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ivan on 05.03.2018.
 */

public interface ClientFanSerial {
    @GET("/3/tv/{id}?api_key=" + MovieDB.key + "&language=rus")
//https://api.themoviedb.org/3/tv/60735?api_key=c87afad022683444d24949b33d3d0cb6&language=rus
    Observable<Serials> reposSerials(@Path("id") int id);

    // Observable<List<Serials>> reposSerials(@Path("id") int id);

    @GET("/3/tv/popular?api_key=" + MovieDB.key + "&language=ru-ru}")
    Observable<PopularSerials> reposSerialsPopular(@Query("page") int page);

    @GET("/3/genre/tv/list?api_key="+MovieDB.key+"&language=ru")
    Observable<ArrayList<Genres>> reposSerialsGenres();


}
