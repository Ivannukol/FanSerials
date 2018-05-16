package com.example.ivan.fanserial.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ivan on 13.03.2018.
 */

public class SerialsHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 20;
    public static final String DATABESE_NAME = "serialsdb";

    public static final String TABLE_SERIALS = "serials";
    public static final String KEY_IDSERIALS = "_idSerials";
    public static final String KEY_NAME = "name";
    public static final String KEY_SERIALS_SEE = "seeSerial";


    public static final String TABLE_SEASONS = "seasons";
    public static final String KEY_IDSESONS = "_idSesons";
    public static final String KEY_SEASONS_NUMBER = "seasonNumber";
    public static final String KEY_SEASONS_SEE = "seeSeason";


    public static final String TABLE_SERIES = "series";
    public static final String KEY_IDSERIES = "_idSeries";
    public static final String KEY_SERIES_NUMBER = "seriesNumber";
    public static final String KEY_SERIES_SEE = "seeSeries";


    public SerialsHelper(Context context) {
        super(context, DATABESE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_SERIALS + "(" +
                KEY_IDSERIALS + " integer primary key,"
                + KEY_NAME + " text,"
                + KEY_SERIALS_SEE + " integer" + ")");


        db.execSQL("create table " + TABLE_SEASONS + "(" +
                KEY_IDSESONS + " integer primary key,"
                + KEY_SEASONS_NUMBER + " integer,"
                + KEY_IDSERIALS + " integer,"
                + KEY_SEASONS_SEE + " integer" + ")");

        db.execSQL("create table " + TABLE_SERIES + "(" +
                KEY_IDSERIES + " integer primary key,"
                + KEY_SERIES_NUMBER + " integer,"
                + KEY_SEASONS_NUMBER + " integer,"
                + KEY_IDSERIALS + " integer,"

                + KEY_SERIES_SEE + " integer" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_SERIALS);
        db.execSQL("drop table if exists " + TABLE_SEASONS);
        db.execSQL("drop table if exists " + TABLE_SERIES);

        onCreate(db);
    }
}