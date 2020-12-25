package com.sanaz.iaust.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.sanaz.iaust.hotel.pojo.Search;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        String objectJson = getIntent().getStringExtra("movie_title");
        Search search = new Gson().fromJson(objectJson, Search.class);

        ImageView imgPoster = findViewById(R.id.imgPoster);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtType = findViewById(R.id.txtType);
        TextView txtYear = findViewById(R.id.txtYear);
        TextView txtId = findViewById(R.id.txtId);
        Button btnSave = findViewById(R.id.btnSave);

        txtName.setText(search.getTitle());
        txtType.setText(search.getType());
        txtYear.setText(search.getYear());
        txtId.setText(search.getImdbID());

        Picasso.get().load(search.getPoster()).into(imgPoster);

        //save to database
        myDatabase database = new myDatabase(MoreInfoActivity.this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.insertMovie(txtName.getText().toString(), txtType.getText().toString(), Integer.parseInt(txtYear.getText().toString()));
            }
        });

    }
}