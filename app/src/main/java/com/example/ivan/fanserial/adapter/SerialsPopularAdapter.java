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
import com.example.ivan.fanserial.Model.Genres;
import com.example.ivan.fanserial.Model.Result;
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

    private Result item;


    @Override
    public SerialPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Genres d = new Genres();
        genres.clear();
        d.setId(35);
        d.setName("Комедія");
        genres.add(d);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_items_serials, parent, false);
        return new SerialPopularViewHolder(v, data);
        //new SerialPopularViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_items_serials, parent, false));
    }

    @Override
    public void onBindViewHolder(SerialPopularViewHolder holder, int position) {
        if (holder instanceof SerialPopularViewHolder) {
            SerialPopularViewHolder rowHolder = (SerialPopularViewHolder) holder;
        }
        item = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(MovieDB.imageUrl + item.getPoster_path())
                .into(holder.imPoster);
        holder.tvName.setText(item.getName());
        holder.tvTypeSerial.setText(getGenres(item.getGenre_ids()));
        //    holder.tvTypeSerial.setText(item.getGenre_ids().get(0)+" ");

    }

    public static final ArrayList<Genres> genres = new ArrayList<>();

    private String getGenres(ArrayList<Integer> genre) {
        String s = "";
        for (Integer i : genre) {
            for (Genres g : genres)
                if (g.getId() == i)
                    s += " " + g.getName();
        }


        return s;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Result> result) {
        data.addAll(result);
        notifyDataSetChanged();
    }


    class SerialPopularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imPoster, dotMenu;
        TextView tvName, tvTypeSerial;
        private SQLiteDatabase database;
        private SerialsHelper serialsHelper;
        private List<Result> data = new ArrayList<>();

        public SerialPopularViewHolder(View v, List<Result> data) {
            super(v);
            this.data = data;
            v.setOnClickListener(this);
            imPoster = v.findViewById(R.id.imPoster);
            tvName = v.findViewById(R.id.tvNameSerial);
            dotMenu = v.findViewById(R.id.dotMenu);
            tvTypeSerial = v.findViewById(R.id.tvTypeSerial);
        }


        @Override
        public void onClick(View v) {
            Log.d("Mylog", "onClick " + data.get(getAdapterPosition()).getName() + " hhh");

            dotMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serialsHelper = new SerialsHelper(v.getContext());

                    popupMenu(v);
                }
            });


        }


        void popupMenu(View v) {
            PopupMenu popup = new PopupMenu(dotMenu.getContext(), dotMenu);
            popup.inflate(R.menu.serials);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem items) {

                    switch (items.getItemId()) {
                        case R.id.itListAdd: {
                            Log.d("myLog",data.get(getAdapterPosition()).getId()+"g    "+data.get(getAdapterPosition()).getName());
                            database = serialsHelper.getWritableDatabase();

                            ContentValues contentValues = new ContentValues();
                            contentValues.put(SerialsHelper.KEY_IDSERIALS, data.get(getAdapterPosition()).getId());
                            contentValues.put(SerialsHelper.KEY_NAME, data.get(getAdapterPosition()).getName());
                            database.insert(SerialsHelper.TABLE_SERIALS, null, contentValues);
                            database.close();
                            return true;
                        }
                        case R.id.itAboutSeries: {
                            database = serialsHelper.getWritableDatabase();
                            ContentValues contentValues = new ContentValues();
                            Cursor cursor = database.query(SerialsHelper.TABLE_SERIALS, null, null, null, null, null, null);
                            if (cursor.moveToFirst()) {
                                int idIndex = cursor.getColumnIndex(SerialsHelper.KEY_IDSERIALS);
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

    }

}
