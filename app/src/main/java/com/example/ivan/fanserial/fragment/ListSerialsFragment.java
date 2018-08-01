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
import com.example.ivan.fanserial.adapter.SerialsAdapter;
import com.example.ivan.fanserial.model.Serials;
import com.example.ivan.fanserial.ui.main.ListSerialsPresenter;
import com.example.ivan.fanserial.ui.main.ListSerialsViwe;
// список серіалів
public class ListSerialsFragment extends Fragment implements ListSerialsViwe {
    private SerialsAdapter serialsAdapter;
    private RecyclerView rv;
    private ProgressBar pbListSerias;
    private SwipeRefreshLayout slrListSerials;
    private View v;
    private ListSerialsPresenter listSerialsPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.action_list_serials, container, false);
        listSerialsPresenter = new ListSerialsPresenter(this);
        pbListSerias = v.findViewById(R.id.pbListSerias);
        slrListSerials = v.findViewById(R.id.slrListSerials);
        rv = v.findViewById(R.id.rvListSerials);

        getList();
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
        serialsAdapter = new SerialsAdapter();
        rv.setAdapter(serialsAdapter);

        serialsAdapter.adapterList = id -> listSerialsPresenter.setDelete(id);
        slrListSerials.setOnRefreshListener(() -> {
            getList();
            slrListSerials.setRefreshing(false);
        });

        return v;
    }

    @Override
    public void setListSerials(Serials serials) {
        serialsAdapter.setData(serials);
        rv.setAdapter(serialsAdapter);
        pbListSerias.setVisibility(ProgressBar.GONE);

    }

    private void getList() {
        if (FanSerialApplication.isConnectedToInternet()) {
            if (listSerialsPresenter.getDbSerial()) {
                serialsAdapter = new SerialsAdapter();
                pbListSerias.setVisibility(ProgressBar.VISIBLE);
                listSerialsPresenter.getDateSeries();
            } else {
                pbListSerias.setVisibility(ProgressBar.GONE);

                Toast.makeText(v.getContext(), "У вас покишо нема серіалів.", (int) 2).show();
            }

        } else {
            Toast.makeText(v.getContext(), "Інтернет відсутній", (int) 2).show();
            pbListSerias.setVisibility(ProgressBar.GONE);
        }
    }
}
