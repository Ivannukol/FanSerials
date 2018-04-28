package com.example.ivan.fanserial.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ivan.fanserial.model.Episodes;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.adapter.MySeriesAdapter;
import com.example.ivan.fanserial.ui.main.MySeriesViwe;
import com.example.ivan.fanserial.ui.main.MySeriesPresenter;

import java.util.List;

public class MySeriesFragment extends Fragment implements MySeriesViwe {
    MySeriesAdapter mySeriesAdapter;
    RecyclerView rv;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_my_series, container, false);
        progressBar = v.findViewById(R.id.pbMySeries);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        MySeriesPresenter mySeriesPresenter = new MySeriesPresenter(this);
        mySeriesPresenter.getDateSeries();
        rv = v.findViewById(R.id.rvMySerias);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
        return v;
    }


    @Override
    public void setMySeries(List<Episodes> episodes) {
        mySeriesAdapter = new MySeriesAdapter();
        mySeriesAdapter.setDate(episodes);
        rv.setAdapter(mySeriesAdapter);
        progressBar.setVisibility(ProgressBar.INVISIBLE);

    }
}
