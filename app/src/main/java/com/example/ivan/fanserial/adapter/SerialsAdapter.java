package com.example.ivan.fanserial.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivan.fanserial.helper.dao.DaoSerials;
import com.example.ivan.fanserial.model.Genres;
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
    public AdapterList adapterList = null;

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
        holder.tvOriginalName.setText(item.getOriginal_name());
        holder.tvReting.setText(item.getVote_average().toString());
        holder.tvYearsSerial.setText(item.getFirst_air_date());
        String elementName = "";
        for (Genres t : item.getGenres())
            elementName += t.getName() + " ";
        holder.tvTypeSerial.setText(elementName);
        holder.dotMenu.setOnClickListener(t -> {
            popupMenu(t, position);
        });
    }

    private void popupMenu(View v, int position) {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.inflate(R.menu.delete);
        popup.setOnMenuItemClickListener(items -> {
            switch (items.getItemId()) {
                case R.id.itListAdd: {
                    if (adapterList != null)
                        adapterList.onAddToFavoriteClick(data.get(position).getId());
                    data.remove(position);
                    notifyDataSetChanged();

                    return true;
                }


            }
            return false;
        });

        popup.show();


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(Serials serials) {
        data.add(serials);
        notifyDataSetChanged();
    }

    class SerialViewHolder extends RecyclerView.ViewHolder {
        ImageView imPoster, dotMenu;
        TextView tvName, tvTypeSerial, tvYearsSerial, tvReting, tvOriginalName;

        public SerialViewHolder(View itemView) {
            super(itemView);
            imPoster = itemView.findViewById(R.id.imPoster);
            dotMenu = itemView.findViewById(R.id.dotMenu);
            tvTypeSerial = itemView.findViewById(R.id.tvTypeSerial);
            tvName = itemView.findViewById(R.id.tvNameSerial);
            tvReting = itemView.findViewById(R.id.tvReting);
            tvYearsSerial = itemView.findViewById(R.id.tvYearsSerial);
            tvOriginalName = itemView.findViewById(R.id.tvOriginalName);
        }

    }

    public interface AdapterList {
        void onAddToFavoriteClick(int id);
    }

}
