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
import com.example.ivan.fanserial.adapter.MySeriesAdapter;
import com.example.ivan.fanserial.model.Episodes;
import com.example.ivan.fanserial.ui.main.MySeriesPresenter;
import com.example.ivan.fanserial.ui.main.MySeriesViwe;

import java.util.List;
//серії які скоро вийдуть
public class MySeriesFragment extends Fragment implements MySeriesViwe {
    private MySeriesAdapter mySeriesAdapter;
    private RecyclerView rv;
    private ProgressBar progressBar;
    private SwipeRefreshLayout slrMySeries;
    private View v;
    private MySeriesPresenter mySeriesPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_my_series, container, false);

        rv = v.findViewById(R.id.rvMySerias);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));

        slrMySeries = v.findViewById(R.id.slrMySeries);
        progressBar = v.findViewById(R.id.pbMySeries);

        progressBar.setVisibility(ProgressBar.VISIBLE);

        mySeriesPresenter = new MySeriesPresenter(this);

        mySeriesAdapter = new MySeriesAdapter();
        rv.setAdapter(mySeriesAdapter);

        slrMySeries.setOnRefreshListener(() -> {
            getList();
            slrMySeries.setRefreshing(false);
        });
        getList();



        return v;
    }


    @Override
    public void setMySeries(List<Episodes> episodes) {
        mySeriesAdapter = new MySeriesAdapter();
        mySeriesAdapter.setDate(episodes);
        rv.setAdapter(mySeriesAdapter);
        progressBar.setVisibility(ProgressBar.GONE);

    }

    private void getList() {
        if (FanSerialApplication.isConnectedToInternet()) {

            if(mySeriesPresenter.getDbSerial()) {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                mySeriesPresenter.getDateSeries();
            }else{
                progressBar.setVisibility(ProgressBar.GONE);

                Toast.makeText(v.getContext(), "У вас покишо нема серіалів.", (int) 2).show();


            }




        } else {
            Toast.makeText(v.getContext(), "Інтернет відсутній", (int) 2).show();
            progressBar.setVisibility(ProgressBar.GONE);
        }
    }
}
