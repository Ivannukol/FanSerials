package com.example.ivan.fanserial.adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.ivan.fanserial.FanSerialApplication;
import com.example.ivan.fanserial.helper.dao.DaoSeries;
import com.example.ivan.fanserial.model.Episodes;
import com.example.ivan.fanserial.R;
import com.example.ivan.fanserial.helper.SerialsHelper;


import java.util.ArrayList;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.TVViewHolder> {
    public ArrayList<Episodes> data = new ArrayList<>();
    private SQLiteDatabase database;
    private SerialsHelper serialsHelper;
    // public AdapterActionTVA adapterActionTva = null;
    View v;

    @Override
    public TVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_item_new_serie, parent, false);

        return new TVViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TVViewHolder holder, int position) {
        Episodes episodes = data.get(position);
        //Log.d("mylog", episodes.getName() + " children" + position);
        holder.tvName.setText(episodes.getName());
        holder.tvNumber.setText(episodes.getSeason_number() + "×" + episodes.getEpisode_number());
        holder.tvDate.setText(episodes.getAir_date().toString());

        holder.seria.setOnClickListener(t -> {
            dilog(v, position);
        });


    }

    private void dilog(View v, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("FanSerial")
                .setMessage("Відмітити серію " + data.get(position).getEpisode_number() + " ?")
                .setCancelable(false)
                .setPositiveButton("Tак",
                        (dialog, id) -> {
                          idSeria.add(data.get(position).getId());

                            DaoSeries daoSeries = new DaoSeries();
                            daoSeries.add(data.get(position).getId(), data.get(position).getEpisode_number(),data.get(position).getSeason_number(), data.get(position).getSeason_number());
                            for (int i = 0; i < idSeria.size(); i++) {
                                for (int j = 0; j < data.size(); j++) {
                                    if (data.get(j).getId() == idSeria.get(i)) {
                                        data.remove(j);
                                    }
                                }
                            }

                            notifyDataSetChanged();
                            dialog.cancel();
                        }
                )
                .setNegativeButton("Ні",
                        (dialog, id) -> {
                            dialog.cancel();
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    ArrayList<Integer> idSeria = new ArrayList<>();

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<Episodes> data) {
        serialsHelper = new SerialsHelper(FanSerialApplication.getInstanse());
        this.data = data;
        notifyDataSetChanged();
    }

    public class TVViewHolder extends RecyclerView.ViewHolder {
        private AdapterView.OnItemClickListener listener;

        TextView tvName, tvDate, tvNumber;
        ConstraintLayout seria;

        public TVViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameSerie);
            seria = itemView.findViewById(R.id.seria);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvNumber = itemView.findViewById(R.id.tvNumber);

        }
    }

    public interface AdapterActionTVA {
        void onAddToFavoriteClick(Episodes item);

    }
}
