package com.example.ivan.fanserial;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivan.fanserial.model.Result;
import com.example.ivan.fanserial.model.Test;

public class About extends AppCompatActivity {
    TextView tvAboutName, tvAboutOther;
    ImageView ivAboutPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Result item = (Result) getIntent().getSerializableExtra("result");
        tvAboutName = findViewById(R.id.tvAboutName);
        tvAboutOther = findViewById(R.id.tvAboutOther);

        ivAboutPoster=findViewById(R.id.ivAboutPoster);
        tvAboutName.setText(item.getName());
        tvAboutOther.setText(item.getOverview());
        Glide.with(this)
                .load(MovieDB.imageUrl + item.getPoster_path())
                .into(ivAboutPoster);
    if(tvAboutOther.getText().length()==0)
        tvAboutOther.setText("Даних про даний серіал нема(");

    }

}