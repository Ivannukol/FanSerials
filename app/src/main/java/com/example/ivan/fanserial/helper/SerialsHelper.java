package com.example.ivan.fanserial.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ivan on 13.03.2018.
 */

public class SerialsHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =8 ;
    public static final String DATABESE_NAME = "serialsdb";
    public static final String TABLE_SERIALS = "serials";
    public static final String TABLE_SEASONS = "seasons";


    public static final String KEY_IDSERIALS = "_id";
    public static final String KEY_NAME = "name";


   public static final String KEY_IDSESONS = "_id";
    public static final String KEY_SEASONS = "season";
    public static final String KEY_SERIES = "series";
    public static final String KEY_SEE = "see";





    public SerialsHelper(Context context) {
        super(context, DATABESE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_SERIALS + "(" +
                KEY_IDSERIALS + " integer primary key,"
                + KEY_NAME + " text" +")");


        db.execSQL("create table " + TABLE_SEASONS + "(" +
                KEY_IDSESONS + " integer primary key,"
                + KEY_SEASONS + " integer,"
                + KEY_SERIES + " integer,"
                + KEY_SEE + " integer" +")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_SERIALS);
        onCreate(db);
    }
}