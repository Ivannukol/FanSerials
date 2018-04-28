package com.example.ivan.fanserial.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivan.fanserial.model.Serials;
import com.example.ivan.fanserial.MovieDB;
import com.example.ivan.fanserial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 11.03.2018.
 */

public class SerialsAdapter extends RecyclerView.Adapter<SerialsAdapter.SerialViewHolder> {

    private List<Serials> data = new ArrayList<>();

    @Override
    public SerialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SerialViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_items_serials, parent, false));
    }

    @Override
    public void onBindViewHolder(SerialViewHolder holder, int position) {
        Serials item = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(MovieDB.imageUrl + item.getSeasons().get(0).getPoster_path())
                .into(holder.imPoster);
        holder.tvName.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Serials> serials) {
        data.addAll(serials);
        notifyDataSetChanged();
    }

    class SerialViewHolder extends RecyclerView.ViewHolder {
        ImageView imPoster, dotMenu;
        TextView tvName;

        public SerialViewHolder(View itemView) {
            super(itemView);
            imPoster = itemView.findViewById(R.id.imPoster);
            tvName = itemView.findViewById(R.id.tvNameSerial);
            dotMenu = itemView.findViewById(R.id.dotMenu);
        }

    }


}
