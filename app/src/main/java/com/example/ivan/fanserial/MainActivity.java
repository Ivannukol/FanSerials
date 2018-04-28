package com.example.ivan.fanserial;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ivan.fanserial.fragment.MySeriesFragment;
import com.example.ivan.fanserial.fragment.NewSeriesFragment;
import com.example.ivan.fanserial.fragment.SerialFragment;


public class MainActivity extends AppCompatActivity   {
    BottomNavigationView bottomNavigationView;
    FragmentTransaction fTrans;
    SerialFragment serialFragment;
    NewSeriesFragment newSeriesFragment;
    MySeriesFragment mySeriesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        serialFragment = new SerialFragment();
        newSeriesFragment = new NewSeriesFragment();
        mySeriesFragment = new MySeriesFragment();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, serialFragment);
        fTrans.commit();



        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                    fTrans = getFragmentManager().beginTransaction();
                    Menu menu = bottomNavigationView.getMenu();

                    MenuItem menuItem=null;
                    switch (item.getItemId()) {
                        case R.id.iList:
                            menuItem = menu.getItem(0);
                            Log.d("mLog","menu");
                            fTrans.replace(R.id.frgmCont, serialFragment);

                            break;
                        case R.id.iMyList:
                            menuItem = menu.getItem(1);

                            fTrans.replace(R.id.frgmCont, mySeriesFragment);

                            break;


                        case R.id.iNewSeries:
                            menuItem = menu.getItem(2);

                            fTrans.replace(R.id.frgmCont, newSeriesFragment);
                            break;

                    }
                    menuItem.setChecked(true);
                    fTrans.commit();

                    return false;
                }
        );

    }

}