package com.example.ivan.fanserial.fragment;

<<<<<<< HEAD
import android.os.Bundle;
import android.support.v4.app.Fragment;
=======
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
>>>>>>> origin/dev
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

<<<<<<< HEAD
import com.example.ivan.fanserial.FanSerialApplication;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.example.ivan.fanserial.model.Result;
=======
import com.example.ivan.fanserial.About;
import com.example.ivan.fanserial.FanSerialApplication;
import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.example.ivan.fanserial.model.Test;
>>>>>>> origin/dev
import com.example.ivan.fanserial.ui.main.SerialPresenter;
import com.example.ivan.fanserial.ui.main.SerialView;

import java.util.ArrayList;

/**
 * Created by Ivan on 21.03.2018.
 */
<<<<<<< HEAD
//популярні серіали
=======

>>>>>>> origin/dev
public class SerialFragment extends Fragment implements SerialView {
    private SerialPresenter serialPresenter;
    private View v;
    private SerialsPopularAdapter serialsPopularAdapter;
    private ProgressBar progressBar;
    private int page = 1;
    private int y = 0;
    private SwipeRefreshLayout slrSerials;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        serialsPopularAdapter = new SerialsPopularAdapter();
        v = inflater.inflate(R.layout.activity_serial, container, false);
        slrSerials = v.findViewById(R.id.slrSerials);
        progressBar = v.findViewById(R.id.pbSerias);
        serialPresenter = new SerialPresenter(this);
        rv = v.findViewById(R.id.rvSerials);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rv.setAdapter(serialsPopularAdapter);
        getList();

        serialsPopularAdapter.adapterAction = new SerialsPopularAdapter.AdapterAction() {
            @Override
            public void onAddToFavoriteClick(Result item) {
                serialPresenter.addToFavoriteSerial(item);
            }

            @Override
            public void onAboutSerialClick(Result item, View v) {

                serialPresenter.onAboutSerial(item, v);
            }


        };

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                y += dy;

                Log.d("rt", "  dy= " + dy + "                 " + y + "               " + page);
<<<<<<< HEAD
                if (y > 4400 * page) {//4600
=======
                if (y > 4600 * page) {
>>>>>>> origin/dev
                    page++;
                    serialPresenter.getPopularSerials(page);
                    y += 100;
                }


            }
        });
        slrSerials.setOnRefreshListener(() -> {
            getList();
            slrSerials.setRefreshing(false);
        });


        return v;
    }

    private void getList() {
        y = 0;
        page = 1;
        if (FanSerialApplication.isConnectedToInternet()) {
            progressBar.setVisibility(ProgressBar.VISIBLE);
            serialPresenter.getPopularSerials(page);
        } else {
            Toast.makeText(v.getContext(), "Інтернет відсутній", (int) 2).show();
            progressBar.setVisibility(ProgressBar.GONE);

        }
    }

    @Override
    public void setSerials(ArrayList<Result> serials) {
        serialsPopularAdapter.setData(serials);
        progressBar.setVisibility(ProgressBar.GONE);

    }

}