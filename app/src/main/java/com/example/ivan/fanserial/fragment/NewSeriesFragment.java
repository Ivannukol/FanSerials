package com.example.ivan.fanserial.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ivan.fanserial.FanSerialApplication;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.ui.main.NewSerialPresenter;
import com.example.ivan.fanserial.ui.main.NewSerialViwe;

import java.util.List;
//серії для відмічання
public class NewSeriesFragment extends Fragment implements NewSerialViwe {

    private View v;
    private SerialsPopularAdapter newSeriesAdapter;
    private RecyclerView rv;
    private ProgressBar progressBar;
    private NewSerialPresenter newSerialPresent;
    private SwipeRefreshLayout slrNewSeries;
    private int position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        position = 0;
        v = inflater.inflate(R.layout.activity_new_series, container, false);
        progressBar = v.findViewById(R.id.pbNewSeries);
        newSerialPresent = new NewSerialPresenter(this);
        slrNewSeries = v.findViewById(R.id.slrNewSeries);
        getList(true);
        rv = v.findViewById(R.id.rvNewSerial);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
        newSeriesAdapter = new SerialsPopularAdapter();
        rv.setAdapter(newSeriesAdapter);

        slrNewSeries.setOnRefreshListener(() -> {
            position = 0;
            getList(true);

            slrNewSeries.setRefreshing(false);

        });




        return v;
    }

    @Override
    public void setEpisod(List<Result> detalisSeasons,boolean flag) {

        newSeriesAdapter.expand = true;
        position = newSeriesAdapter.setElement(detalisSeasons, newSerialPresent, position);
        if(flag)
        rv.setAdapter(newSeriesAdapter);

        progressBar.setVisibility(ProgressBar.GONE);
    }

    private void getList(boolean flag) {
        if (FanSerialApplication.isConnectedToInternet()) {
            if (flag) {
                newSeriesAdapter = new SerialsPopularAdapter();
            }
            if (newSerialPresent.getDbSerial() != 0) {
                newSerialPresent.getDetalisSeasons(position);
                position++;
            } else {
                progressBar.setVisibility(ProgressBar.GONE);
                Toast.makeText(v.getContext(), "У вас покишо нема серіалів.", 2).show();
            }
        } else {
            Toast.makeText(v.getContext(), "Інтернет відсутній", 2).show();
            progressBar.setVisibility(ProgressBar.GONE);
        }
    }
}

