package com.example.ivan.fanserial.adapter;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 12.03.2018.
 */

public class SerialsPopularAdapter extends RecyclerView.Adapter<SerialsPopularAdapter.SerialPopularViewHolder> {

    private List<Result> data = new ArrayList<>();

    @Override
    public SerialPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SerialPopularViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_items_serials, parent, false));
    }

    @Override
    public void onBindViewHolder(SerialPopularViewHolder holder, int position) {
        Result item = data.get(position);
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
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.itListAdd: {
                                Log.d("MyLog","add"+data.get(position).getName());
                                return true;
                            }
                            case R.id.itAboutSeries: {
                                Log.d("MyLog","about");

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
