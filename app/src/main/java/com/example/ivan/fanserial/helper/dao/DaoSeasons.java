package com.example.ivan.fanserial.helper.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ivan.fanserial.FanSerialApplication;
import com.example.ivan.fanserial.helper.SerialsHelper;
import com.example.ivan.fanserial.helper.model.SeasonsDB;

import java.util.ArrayList;

public class DaoSeasons {
    private SQLiteDatabase database;
    private SerialsHelper serialsHelper;

    public DaoSeasons() {
        serialsHelper = new SerialsHelper(FanSerialApplication.getInstanse());

    }

    public ArrayList<SeasonsDB> select() {
        database = serialsHelper.getWritableDatabase();
        ArrayList<SeasonsDB> seasons = new ArrayList<>();
        Cursor cursor = database.query(SerialsHelper.TABLE_SEASONS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idSesons = cursor.getColumnIndex(SerialsHelper.KEY_IDSESONS);
            int idSerials = cursor.getColumnIndex(SerialsHelper.KEY_IDSERIALS);
            int idSeasonsNumber = cursor.getColumnIndex(SerialsHelper.KEY_SEASONS_NUMBER);
            int idSeasonsSee = cursor.getColumnIndex(SerialsHelper.KEY_SEASONS_SEE);

            SeasonsDB item;
            do {
                try {
                    item = new SeasonsDB();
                    item.set_idSerials(cursor.getInt(idSerials));
                    item.set_idSesons(cursor.getInt(idSesons));
                    item.setSeasonNumber(cursor.getInt(idSeasonsNumber));
                    item.setSeeSeason(cursor.getInt(idSeasonsSee));
                    seasons.add(item);
                } catch (Exception e) {
                    break;
                }
            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 row Seasons");
        }
        cursor.close();
        database.close();
        return seasons;
    }

    public boolean add(int idSesons, int idSerials, int number, int see) {
        database = serialsHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SerialsHelper.KEY_IDSESONS, idSesons);
        contentValues.put(SerialsHelper.KEY_IDSERIALS, idSerials);
        contentValues.put(SerialsHelper.KEY_SEASONS_NUMBER, number);
        contentValues.put(SerialsHelper.KEY_SEASONS_SEE, see);
        long values = database.insert(SerialsHelper.TABLE_SEASONS, null, contentValues);
        database.close();
        if (values != -1)
            return true;
        else
            return false;

    }

    public boolean delete(int id) {

        database = serialsHelper.getWritableDatabase();

        int values = database.delete(SerialsHelper.TABLE_SEASONS, SerialsHelper.KEY_IDSESONS + " = " + id, null);
        database.close();
        if (values != 0)
            return true;
        else
            return false;
    }
}
