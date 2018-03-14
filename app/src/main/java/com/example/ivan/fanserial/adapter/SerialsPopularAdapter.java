package com.example.ivan.fanserial.adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivan.fanserial.Model.PopularSerials;
import com.example.ivan.fanserial.Model.Result;
import com.example.ivan.fanserial.Model.Serials;
import com.example.ivan.fanserial.MovieDB;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.helper.SerialsHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 12.03.2018.
 */

public class SerialsPopularAdapter extends RecyclerView.Adapter<SerialsPopularAdapter.SerialPopularViewHolder> {

    private List<Result> data = new ArrayList<>();
    private SQLiteDatabase database;
    private SerialsHelper serialsHelper;
    private Result item;

    @Override
    public SerialPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        serialsHelper = new SerialsHelper(parent.getContext());

        return new SerialPopularViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_items_serials, parent, false));
    }

    @Override
    public void onBindViewHolder(SerialPopularViewHolder holder, int position) {


        item = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(MovieDB.imageUrl + item.getPoster_path())
                .into(holder.imPoster);
        holder.tvName.setText(item.getName());

        final ImageView dotMenu = holder.dotMenu;

        holder.dotMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(dotMenu.getContext(), holder.dotMenu);

                popup.inflate(R.menu.serials);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem items) {
                        database = serialsHelper.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        switch (items.getItemId()) {
                            case R.id.itListAdd: {

                                // contentValues.put(SerialsHelper.KEY_ID,item.getId());
                               /* contentValues.put(SerialsHelper.KEY_NAME, item.getName());
                                contentValues.put(SerialsHelper.KEY_SEASONS, 1);
                                contentValues.put(SerialsHelper.KEY_SERIES, 1);
                                contentValues.put(SerialsHelper.KEY_SEE, 1);
                                database.insert(SerialsHelper.TABLE_CONTACTS, null, contentValues);*/
                                Log.d("mLog",  item.getName());

                                database.close();


                                //  Log.d("MyLog", "add" + data.get(position).getName());
                                return true;
                            }
                            case R.id.itAboutSeries: {
                                Cursor cursor = database.query(SerialsHelper.TABLE_CONTACTS, null, null, null, null, null, null);
                                if (cursor.moveToFirst()) {
                                    int idIndex = cursor.getColumnIndex(SerialsHelper.KEY_ID);
                                    int nameIndex = cursor.getColumnIndex(SerialsHelper.KEY_NAME);
                                    do {
                                        Log.d("mLog", "Id = " + cursor.getInt(idIndex) +
                                                ", name = " + cursor.getString(nameIndex)
                                        );

                                    } while (cursor.moveToNext());
                                } else {
                                    Log.d("mLog", "0 row");
                                }
                                cursor.close();
                                Log.d("MyLog", "about1111");
                                database.close();

                                return true;
                            }

                        }
                        return false;
                    }
                });

                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Result> result) {
        data.addAll(result);
        notifyDataSetChanged();
    }

    class SerialPopularViewHolder extends RecyclerView.ViewHolder {
        ImageView imPoster, dotMenu;
        TextView tvName;

        public SerialPopularViewHolder(View itemView) {
            super(itemView);
            imPoster = itemView.findViewById(R.id.imPoster);
            tvName = itemView.findViewById(R.id.tvNameSerial);
            dotMenu = itemView.findViewById(R.id.dotMenu);
            // itemView.registerForContextMenu(tvName);
            // itemView.setOnCreateContextMenuListener();       //dotMenu=itemView.findViewById(R.id.dotMenu);

            // itemView.setOnCreateContextMenuListener(this);

        }

    }


}
