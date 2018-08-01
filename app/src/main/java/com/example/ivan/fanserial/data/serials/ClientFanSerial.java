package com.example.ivan.fanserial.data.serials;

import com.example.ivan.fanserial.model.GetGenresResponse;
import com.example.ivan.fanserial.model.PopularSerials;
import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.model.Serials;
import com.example.ivan.fanserial.MovieDB;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ivan on 05.03.2018.
 */

public interface ClientFanSerial {
    @GET("/3/tv/{id}?api_key=" + MovieDB.key + "&language=rus")
    Observable<Serials> reposSerials(@Path("id") int id);


    @GET("/3/tv/popular?api_key=" + MovieDB.key + "&language=ru-ru}")
    Observable<PopularSerials> reposSerialsPopular(@Query("page") int page);

<<<<<<< HEAD
    @GET("/3/genre/tv/list?api_key=" + MovieDB.key +"&language=ru")
=======
    @GET("/3/genre/tv/list?api_key=c87afad022683444d24949b33d3d0cb6&language=ru")
>>>>>>> origin/dev
    Observable<GetGenresResponse> reposSerialsGenres();

    @GET("/3/tv/{tv_id}/season/{season_number}?api_key="+MovieDB.key+"&language=ru")
    Observable<Result> reposDetalisSeasons(@Path("tv_id") int tv_id, @Path("season_number") int season_number);

}
