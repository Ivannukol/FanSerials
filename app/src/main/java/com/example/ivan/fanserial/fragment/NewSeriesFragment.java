package com.example.ivan.fanserial.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ivan.fanserial.FanSerialApplication;
import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.example.ivan.fanserial.ui.main.NewSerialPresenter;
import com.example.ivan.fanserial.ui.main.NewSerialViwe;

import java.util.ArrayList;
import java.util.List;

public class NewSeriesFragment extends Fragment implements NewSerialViwe {

    private View v;
    private SerialsPopularAdapter newSeriesAdapter;
    private RecyclerView rv;
    private ProgressBar progressBar;
    private NewSerialPresenter newSerialPresent;
    private SwipeRefreshLayout slrNewSeries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_new_series, container, false);
        progressBar = v.findViewById(R.id.pbNewSeries);
        newSerialPresent = new NewSerialPresenter(this);
        slrNewSeries = v.findViewById(R.id.slrNewSeries);
        getList();
        rv = v.findViewById(R.id.rvNewSerial);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
        newSeriesAdapter = new SerialsPopularAdapter();
        rv.setAdapter(newSeriesAdapter);

        slrNewSeries.setOnRefreshListener(() -> {
           getList();
            slrNewSeries.setRefreshing(false);

        });
        return v;
    }

    @Override
    public void setEpisod(List<Result> detalisSeasons) {
        newSeriesAdapter.expand = true;
        newSeriesAdapter.setElement(detalisSeasons);
        rv.setAdapter(newSeriesAdapter);
        progressBar.setVisibility(ProgressBar.GONE);
    }

    private void getList() {
        if (FanSerialApplication.isConnectedToInternet()) {
            newSeriesAdapter = new SerialsPopularAdapter();
            progressBar.setVisibility(ProgressBar.VISIBLE);
            newSerialPresent.getDetalisSeasons();
        } else {
            Toast.makeText(v.getContext(), "Інтернет відсутній", (int) 2).show();
            progressBar.setVisibility(ProgressBar.GONE);
        }
    }
}

