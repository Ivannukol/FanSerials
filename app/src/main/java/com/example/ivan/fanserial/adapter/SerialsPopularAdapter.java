package com.example.ivan.fanserial.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivan.fanserial.model.Genres;
import com.example.ivan.fanserial.model.GetGenresResponse;
import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.MovieDB;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.data.serials.Genrest;
import com.example.ivan.fanserial.data.serials.GetGenres;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 12.03.2018.
 */

public class SerialsPopularAdapter extends RecyclerView.Adapter<SerialsPopularAdapter.SerialPopularViewHolder> implements Genrest {

    private List<Result> data = new ArrayList<>();

    private Result item;
    public ArrayList<Genres> genres = new ArrayList<>();
    public boolean expand = false;
    public AdapterAction adapterAction = null;
    private boolean hide = true;
    GetGenresResponse getGenresResponse = new GetGenresResponse();

    @Override
    public SerialPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_items_serials, parent, false);
        return new SerialPopularViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SerialPopularViewHolder holder, int position) {
        item = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(MovieDB.imageUrl + item.getPoster_path())
                .into(holder.imPoster);
        holder.tvReting.setText("");
        holder.tvTypeSerial.setText("");
        holder.tvName.setText(item.getName());
        holder.tvTypeSerial.setText(item.getGenres().toString() + "");
        holder.tvYearsSerial.setText(item.getFirst_air_date());
        holder.tvReting.setText("Райтинг: " + item.getVote_average());
        holder.tvOriginalName.setText(item.getOriginal_name());

        if (expand) {
            holder.tvName.setText(item.getOriginal_name());
            holder.tvOriginalName.setText("");
            holder.tvTypeSerial.setText(item.getName());
            holder.tvReting.setText("");
            holder.adapter = new TVAdapter();
            holder.rvTV.setAdapter(holder.adapter);
            holder.adapter.setData(item.getEpisodes());
            holder.rvTV.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
            holder.rvTV.requestFocus();

        }else


        holder.dotMenu.setOnClickListener(v ->
                {
                    popupMenu(v, item,position);
                }

        );

        holder.constraintLayout.setOnClickListener(v -> {
            if (expand) {
               holder.rvTV.setVisibility(View.VISIBLE);
                hide = !hide;

                if (hide)
                    holder.rvTV.setVisibility(View.GONE);

            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setData(List<Result> result) {
        GetGenres getGenres = new GetGenres(this);
        getGenres.getGenrest();
        data.addAll(result);
        notifyDataSetChanged();
    }

    @Override
    public void setGenres(GetGenresResponse getGenresResponse) {
        this.getGenresResponse = getGenresResponse;
    }

    private void popupMenu(View v, Result item,int position) {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.inflate(R.menu.serials);
        popup.setOnMenuItemClickListener(items -> {
            switch (items.getItemId()) {
                case R.id.itListAdd: {
                    if (adapterAction != null)
                        adapterAction.onAddToFavoriteClick(data.get(position));
                    return true;
                }
               /* case R.id.itAboutSeries: {
                    if (adapterAction != null)
                        adapterAction.onAboutSerialClick(item);
                    return true;
                }*/

            }
            return false;
        });

        popup.show();


    }


    class SerialPopularViewHolder extends RecyclerView.ViewHolder {
        ImageView imPoster, dotMenu;
        TextView tvName, tvTypeSerial, tvYearsSerial, tvReting, tvOriginalName;
        RecyclerView rvTV, rvNewSerial;
        TVAdapter adapter;
        ConstraintLayout constraintLayout;

        public SerialPopularViewHolder(View v) {
            super(v);
            constraintLayout = v.findViewById(R.id.clSerialsItem);

            imPoster = v.findViewById(R.id.imPoster);
            dotMenu = v.findViewById(R.id.dotMenu);
            rvTV = v.findViewById(R.id.rvTV);
            rvNewSerial = v.findViewById(R.id.rvNewSerial);
            tvTypeSerial = v.findViewById(R.id.tvTypeSerial);
            tvName = v.findViewById(R.id.tvNameSerial);
            tvReting = v.findViewById(R.id.tvReting);
            tvYearsSerial = v.findViewById(R.id.tvYearsSerial);
            tvOriginalName = v.findViewById(R.id.tvOriginalName);
        }

    }


    public interface AdapterAction {
        void onAddToFavoriteClick(Result item);

        void onAboutSerialClick(Result item);
    }
}
