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
import com.example.ivan.fanserial.helper.dao.DaoSeries;
import com.example.ivan.fanserial.model.Episodes;
import com.example.ivan.fanserial.model.Genres;
import com.example.ivan.fanserial.model.GetGenresResponse;
import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.MovieDB;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.data.serials.Genrest;
import com.example.ivan.fanserial.data.serials.GetGenres;
<<<<<<< HEAD
import com.example.ivan.fanserial.ui.main.NewSerialPresenter;
=======
>>>>>>> origin/dev

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 12.03.2018.
 */

public class SerialsPopularAdapter extends RecyclerView.Adapter<SerialsPopularAdapter.SerialPopularViewHolder> implements Genrest {

    private List<Result> data = new ArrayList<>();
    private Result item;
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
        String elementName = "";
        for (Genres t : item.getGenres())
<<<<<<< HEAD
           // elementName += t.getName() + " ";
        holder.tvTypeSerial.append(t.getName() + " ");//setText(elementName);
=======
            elementName += t.getName() + " ";
        holder.tvTypeSerial.setText(elementName);
>>>>>>> origin/dev
        holder.tvYearsSerial.setText(item.getFirst_air_date());
        holder.tvReting.setText("Рейтинг: " + item.getVote_average());
        holder.tvOriginalName.setText(item.getOriginal_name());

        if (expand) {
            holder.tvName.setText(item.getOriginal_name());
            holder.tvOriginalName.setText("");
            holder.tvTypeSerial.setText(item.getName());
            holder.tvReting.setText("");
            holder.adapter = new TVAdapter();
            holder.rvTV.setAdapter(holder.adapter);
<<<<<<< HEAD
            for (int i = 0; i < item.getEpisodes().size(); i++) {
                if (item.getEpisodes().get(i).getAir_date() != null) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = format.parse(item.getEpisodes().get(i).getAir_date());
                        if (System.currentTimeMillis() < date.getTime()) {
                            item.getEpisodes().remove(i);
                        }
                        {

                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }


=======
>>>>>>> origin/dev
            holder.adapter.setData(item.getEpisodes());
            holder.rvTV.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
            holder.rvTV.requestFocus();
            holder.dotMenu.setOnClickListener(v ->
                    {
                        popupMenuSeeSesons(v, position);
                    }


            );
        } else
            holder.dotMenu.setOnClickListener(v ->
                    {
                        popupMenuInfo(v, position);
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

    private void popupMenuSeeSesons(View v, int position) {

        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.inflate(R.menu.see_sesons);
        popup.setOnMenuItemClickListener(items -> {

            DaoSeries daoSeries = new DaoSeries();
            for (Episodes e : data.get(position).getEpisodes())
                daoSeries.add(e.getId(), e.getEpisode_number(), e.getSeason_number(), e.getSeason_number());
            data.remove(position);
            notifyDataSetChanged();
            return true;
        });

        popup.show();

    }


    @Override
    public int getItemCount() {


        return data.size();
    }


    public void setData(List<Result> result) {
        GetGenres getGenres = new GetGenres(this);
        getGenres.getGenrest();
        data.addAll(result);
<<<<<<< HEAD

=======
        /*if (data.size() > 100) {
            for (int i = 0; i < 20; i++)
                data.remove(0);


        }*/
>>>>>>> origin/dev
        notifyDataSetChanged();

    }

<<<<<<< HEAD
    public int setElement(List<Result> result, NewSerialPresenter newSerialPresent, int position) {
        data.addAll(result);
            newSerialPresent.getDetalisSeasons(position);
            position++;
        return position;
=======
    public void setElement(List<Result> result) {

        if (result.size() != 0) {
            data.add(result.get(0));
        }else
        data.addAll(result);
>>>>>>> origin/dev
    }

    @Override
    public void setGenres(GetGenresResponse getGenresResponse) {
        this.getGenresResponse = getGenresResponse;
    }

    private void popupMenuInfo(View v, int position) {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.inflate(R.menu.serials);
        popup.setOnMenuItemClickListener(items -> {
            switch (items.getItemId()) {
                case R.id.itListAdd: {
                    if (adapterAction != null)
                        adapterAction.onAddToFavoriteClick(data.get(position));
                    return true;
                }
                case R.id.itAboutSeries: {
                    if (adapterAction != null)
                        adapterAction.onAboutSerialClick(data.get(position), v);
                    return true;
                }
<<<<<<< HEAD

            }
            return false;
        });

        popup.show();


    }

=======

            }
            return false;
        });

        popup.show();


    }

>>>>>>> origin/dev

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

        void onAboutSerialClick(Result item, View v);
    }
}
