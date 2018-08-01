package com.example.ivan.fanserial;

<<<<<<< HEAD
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
=======
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivan.fanserial.Model.PopularSerials;
import com.example.ivan.fanserial.Model.Serials;
import com.example.ivan.fanserial.Present.PresentorRetrofiltr;
import com.example.ivan.fanserial.PresentIntrfes.RetrofiltrContractView;
import com.example.ivan.fanserial.adapter.SerialsAdapter;
import com.example.ivan.fanserial.adapter.SerialsPopularAdapter;
import com.example.ivan.fanserial.helper.SerialsHelper;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RetrofiltrContractView {
       PresentorRetrofiltr presentorRetrofiltr;
>>>>>>> parent of 0a0ecb3... end


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
<<<<<<< HEAD

        load(new SerialFragment());


        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
=======
>>>>>>> origin/dev
=======
        presentorRetrofiltr= new PresentorRetrofiltr(this);
>>>>>>> parent of 0a0ecb3... end

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

=======
>>>>>>> parent of 0a0ecb3... end
    @Override
    public void showRecyclerView(SerialsPopularAdapter data) {
        RecyclerView rv = findViewById(R.id.rvSerials);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(data);
    }

}