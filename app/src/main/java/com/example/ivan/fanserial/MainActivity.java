package com.example.ivan.fanserial;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ivan.fanserial.Present.PresentorRetrofiltr;
import com.example.ivan.fanserial.PresentIntrfes.RetrofiltrContractView;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;


public class MainActivity extends AppCompatActivity implements RetrofiltrContractView {
    PresentorRetrofiltr presentorRetrofiltr;
    BottomNavigationView bottomNavigationView;
    FragmentTransaction fTrans;
    Serial serial;
    NewSeries newSeries;
    MySeries mySeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serial = new Serial();
        newSeries = new NewSeries();
        mySeries = new MySeries();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, serial);
        fTrans.commit();


        presentorRetrofiltr = new PresentorRetrofiltr(this);
        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                    fTrans = getFragmentManager().beginTransaction();
                    Menu menu = bottomNavigationView.getMenu();

                    MenuItem menuItem=null;
                    switch (item.getItemId()) {
                        case R.id.iList:
                            menuItem = menu.getItem(0);
                            Log.d("mLog","menu");
                            fTrans.replace(R.id.frgmCont, serial);
                            presentorRetrofiltr = new PresentorRetrofiltr(this);

                            break;
                        case R.id.iMyList:
                            menuItem = menu.getItem(1);

                            fTrans.replace(R.id.frgmCont, mySeries);
                            break;


                        case R.id.iNewSeries:
                            menuItem = menu.getItem(2);

                            fTrans.replace(R.id.frgmCont, newSeries);
                            break;

                    }
                    menuItem.setChecked(true);
                    fTrans.commit();

                    return false;
                }
        );

    }

    @Override
    public void showRecyclerView(SerialsPopularAdapter data) {
        RecyclerView rv = findViewById(R.id.rvSerials);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(data);
    }

}