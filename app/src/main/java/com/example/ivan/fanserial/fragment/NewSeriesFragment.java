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
import com.example.ivan.fanserial.ui.main.NewSerialPresenter;
import com.example.ivan.fanserial.ui.main.NewSerialViwe;

import java.util.ArrayList;
import java.util.List;

public class NewSeriesFragment extends Fragment implements NewSerialViwe {

    View v;
    SerialsPopularAdapter newSeriesAdapter;
    RecyclerView rv;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_new_series, container, false);
         progressBar = v.findViewById(R.id.pbNewSeries);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        NewSerialPresenter newSerialPresent = new NewSerialPresenter(this, inflater);
        newSerialPresent.getDetalisSeasons();
        rv = v.findViewById(R.id.rvNewSerial);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));

        return v;
    }
    @Override
    public void setEpisod(List<Result> detalisSeasons) {
        newSeriesAdapter = new SerialsPopularAdapter();
        newSeriesAdapter.expand = true;
        newSeriesAdapter.setData(detalisSeasons);
        rv.setAdapter(newSeriesAdapter);
        progressBar.setVisibility(ProgressBar.INVISIBLE);

    }
}

