package com.example.ivan.fanserial;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.ivan.fanserial.fragment.ListSerialsFragment;
import com.example.ivan.fanserial.fragment.MySeriesFragment;
import com.example.ivan.fanserial.fragment.NewSeriesFragment;
import com.example.ivan.fanserial.fragment.SerialFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load(new SerialFragment());


        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


    }

    private boolean load(Fragment fragment) {
            if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frgmCont, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.iList:
                fragment = new SerialFragment();
                break;
            case R.id.iMyList:
                fragment = new MySeriesFragment();
                break;
            case R.id.iNewSeries:
                fragment = new NewSeriesFragment();
                break;
            case R.id.iListSerials:
                fragment = new ListSerialsFragment();
                break;

        }

        return load(fragment);
    }
}