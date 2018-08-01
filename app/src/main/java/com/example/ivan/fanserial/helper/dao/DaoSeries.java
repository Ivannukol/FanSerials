package com.example.ivan.fanserial.helper.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ivan.fanserial.FanSerialApplication;
import com.example.ivan.fanserial.helper.SerialsHelper;
import com.example.ivan.fanserial.helper.model.SeriesDB;

import java.util.ArrayList;

public class DaoSeries {
    private SQLiteDatabase database;
    private SerialsHelper serialsHelper;

    public DaoSeries() {
        serialsHelper = new SerialsHelper(FanSerialApplication.getInstanse());

    }

    public ArrayList<SeriesDB> select() {
        database = serialsHelper.getWritableDatabase();
        ArrayList<SeriesDB> seriesDBS = new ArrayList<>();
        Cursor cursor = database.query(SerialsHelper.TABLE_SERIES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idSeries = cursor.getColumnIndex(SerialsHelper.KEY_IDSERIES);
            int idSerials = cursor.getColumnIndex(SerialsHelper.KEY_IDSERIALS);
            int idSeriesNumber = cursor.getColumnIndex(SerialsHelper.KEY_SERIES_NUMBER);
            int idSeriessSee = cursor.getColumnIndex(SerialsHelper.KEY_SERIES_SEE);
            int idSesonsNumber = cursor.getColumnIndex(SerialsHelper.KEY_SEASONS_NUMBER);

            SeriesDB item;
            do {
                try {
                    item = new SeriesDB();
                    item.set_idSeries(cursor.getInt(idSeries));
                    item.set_idSerials(cursor.getInt(idSerials));
                    item.setSeriesNumber(cursor.getInt(idSeriesNumber));
                    item.setSeeSeries(cursor.getInt(idSeriessSee));
                    item.setSeasonNumber(cursor.getInt(idSesonsNumber));
                    seriesDBS.add(item);
                } catch (Exception e) {
                    break;
                }
            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 row");
        }
        cursor.close();
        database.close();
        return seriesDBS;
    }

    public boolean add(int idSerial,  int numberSeries,  int numberSesons,int see) {
        database = serialsHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
      //  contentValues.put(SerialsHelper.KEY_IDSERIES, idSeries);
        contentValues.put(SerialsHelper.KEY_IDSERIALS, idSerial);
        contentValues.put(SerialsHelper.KEY_SERIES_NUMBER, numberSeries);
        contentValues.put(SerialsHelper.KEY_SEASONS_NUMBER, numberSesons);
        contentValues.put(SerialsHelper.KEY_SERIES_SEE, see);
        long values = database.insert(SerialsHelper.TABLE_SERIES, null, contentValues);
        database.close();
        if (values != -1)
            return true;
        else
            return false;

    }

    public boolean delete(int id) {

        database = serialsHelper.getWritableDatabase();

        int values = database.delete(SerialsHelper.TABLE_SERIES, SerialsHelper.KEY_IDSERIES + " = " + id, null);
        database.close();
        if (values != 0)
            return true;
        else
            return false;
    }
}
