package com.example.ivan.fanserial.adapter;



import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivan.fanserial.model.Episodes;
import com.example.ivan.fanserial.MovieDB;
import com.example.ivan.fanserial.R;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
//серії які скоро вийдуть
=======

>>>>>>> origin/dev
public class MySeriesAdapter extends RecyclerView.Adapter<MySeriesAdapter.MySeriesHolder> {
   List<Episodes> date = new ArrayList<>();

    @Override
    public MySeriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MySeriesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item_exit_time, parent, false));
    }

    @Override
    public void onBindViewHolder(MySeriesHolder holder, int position) {
        Episodes item = date.get(position);
        holder.tvNameSeries.setText(item.getName());
        holder.tvNumberEx.setText(item.getSeason_number() + "×" + item.getEpisode_number());
        holder.tvDataSerie.setText(item.getAir_date());
        Glide.with(holder.itemView.getContext())
                .load(MovieDB.imageUrl + item.getPoster())
                .into(holder.imPosterSeries);

    }
    @Override
    public int getItemCount() {
        return date.size();
    }

    public void setDate(List<Episodes> date) {
        this.date = date;
        notifyDataSetChanged();
    }

    public class MySeriesHolder extends RecyclerView.ViewHolder {
        TextView tvNameSeries, tvNumberEx, tvDataSerie;
        ImageView imPosterSeries;

        public MySeriesHolder(View itemView) {
            super(itemView);
            tvNameSeries = itemView.findViewById(R.id.tvNameSeries);
            tvNumberEx = itemView.findViewById(R.id.tvNumberEx);
            tvDataSerie = itemView.findViewById(R.id.tvDataSerie);
            imPosterSeries = itemView.findViewById(R.id.imPosterSeries);
        }
    }
}
