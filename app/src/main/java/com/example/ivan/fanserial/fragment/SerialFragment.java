package com.example.ivan.fanserial.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.example.ivan.fanserial.ui.main.SerialPresenter;
import com.example.ivan.fanserial.ui.main.SerialView;

import java.util.ArrayList;

/**
 * Created by Ivan on 21.03.2018.
 */

public class SerialFragment extends Fragment implements SerialView {
    SerialPresenter serialPresenter;
    View v;
    SerialsPopularAdapter serialsPopularAdapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        serialsPopularAdapter = new SerialsPopularAdapter();
        serialsPopularAdapter.adapterAction = new SerialsPopularAdapter.AdapterAction() {
            @Override
            public void onAddToFavoriteClick(Result item) {
                serialPresenter.addToFavoriteSerial(item);
            }

            @Override
            public void onAboutSerialClick(Result item) {

            }
        };


        v = inflater.inflate(R.layout.activity_serial, container, false);
        progressBar = v.findViewById(R.id.pbSerias);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        serialPresenter = new SerialPresenter(this);
        serialPresenter.getPopularSerials();
        RecyclerView rv = v.findViewById(R.id.rvSerials);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rv.setAdapter(serialsPopularAdapter);
        return v;
    }


    @Override
    public void setSerials(ArrayList<Result> serials) {
        serialsPopularAdapter.setData(serials);
        progressBar.setVisibility(ProgressBar.INVISIBLE);

    }
}