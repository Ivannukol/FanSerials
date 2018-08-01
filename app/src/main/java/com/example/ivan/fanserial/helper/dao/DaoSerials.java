package com.example.ivan.fanserial.helper.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ivan.fanserial.FanSerialApplication;
import com.example.ivan.fanserial.helper.SerialsHelper;
import com.example.ivan.fanserial.helper.model.SerialsDB;

import java.util.ArrayList;

public class DaoSerials {
    private SQLiteDatabase database;
    private SerialsHelper serialsHelper;

    public DaoSerials() {
        serialsHelper = new SerialsHelper(FanSerialApplication.getInstanse());

    }

    public ArrayList<SerialsDB> select() {
        database = serialsHelper.getWritableDatabase();
        ArrayList<SerialsDB> serials = new ArrayList<>();
        Cursor cursor = database.query(SerialsHelper.TABLE_SERIALS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(SerialsHelper.KEY_IDSERIALS);
            int idName = cursor.getColumnIndex(SerialsHelper.KEY_NAME);
            int idSerialsSee = cursor.getColumnIndex(SerialsHelper.KEY_SERIALS_SEE);

            SerialsDB item;
            do {
                try {
                    item = new SerialsDB();
                    item.set_idSerials(cursor.getInt(idIndex));
                    item.setName(cursor.getString(idName));
                    item.setSeeSerial(cursor.getInt(idSerialsSee));
                    serials.add(item);
                } catch (Exception e) {
                    break;
                }
            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 row");
        }
        cursor.close();
        database.close();
        return serials;
    }

    public boolean add(int id, String name, int see) {
        database = serialsHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SerialsHelper.KEY_IDSERIALS, id);
        contentValues.put(SerialsHelper.KEY_NAME, name);
        contentValues.put(SerialsHelper.KEY_SERIALS_SEE, see);
        long values = database.insert(SerialsHelper.TABLE_SERIALS, null, contentValues);
        database.close();
        if (values != -1)
            return true;
        else
            return false;

    }

    public boolean delete(int id) {

        database = serialsHelper.getWritableDatabase();

        int values = database.delete(SerialsHelper.TABLE_SERIALS, SerialsHelper.KEY_IDSERIALS + " = " + id, null);
        database.close();
        if (values != 0)
            return true;
        else
            return false;
    }
}
