package com.example.ivan.fanserial;

<<<<<<< HEAD
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
=======
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
>>>>>>> origin/dev
import android.view.MenuItem;

import com.example.ivan.fanserial.fragment.ListSerialsFragment;
import com.example.ivan.fanserial.fragment.MySeriesFragment;
import com.example.ivan.fanserial.fragment.NewSeriesFragment;
import com.example.ivan.fanserial.fragment.SerialFragment;
<<<<<<< HEAD

=======
import com.example.ivan.fanserial.helper.BottomNavigationViewHelper;
>>>>>>> origin/dev

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction fTrans;
    private SerialFragment serialFragment;
    private NewSeriesFragment newSeriesFragment;
    private MySeriesFragment mySeriesFragment;
    private ListSerialsFragment listSerialsFragment;
    private int element = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

        load(new SerialFragment());


        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
=======
>>>>>>> origin/dev

        serialFragment = new SerialFragment();
        newSeriesFragment = new NewSeriesFragment();
        mySeriesFragment = new MySeriesFragment();
        listSerialsFragment = new ListSerialsFragment();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, serialFragment);
        fTrans.commit();


        bottomNavigationView = findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                    fTrans = getFragmentManager().beginTransaction();
                    Menu menu = bottomNavigationView.getMenu();

                    MenuItem menuItem = null;
                    switch (item.getItemId()) {
                        case R.id.iList:
                            element = R.id.iList;
                            menuItem = menu.getItem(0);
                            fTrans.replace(R.id.frgmCont, serialFragment);

                            break;
                        case R.id.iMyList:
                            element = R.id.iMyList;
                            menuItem = menu.getItem(1);

                            fTrans.replace(R.id.frgmCont, mySeriesFragment);

                            break;


                        case R.id.iNewSeries:
                            element = R.id.iNewSeries;
                            menuItem = menu.getItem(2);

                            fTrans.replace(R.id.frgmCont, newSeriesFragment);
                            break;


                        case R.id.iListSerials:
                            element = R.id.iListSerials;
                            menuItem = menu.getItem(3);

                            fTrans.replace(R.id.frgmCont, listSerialsFragment);
                            break;

                    }
                    menuItem.setChecked(true);
                    fTrans.commit();

                    return false;
                }
        );

<<<<<<< HEAD
    private boolean load(Fragment fragment) {
            if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frgmCont, fragment)
                    .commit();
            return true;
        }
        return false;
=======
>>>>>>> origin/dev
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