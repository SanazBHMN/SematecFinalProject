package com.sanaz.iaust.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.loopj.android.http.*;
import com.sanaz.iaust.hotel.pojo.Movie;
import com.sanaz.iaust.hotel.pojo.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtSearch = findViewById(R.id.edtSearch);
        TextView txtTitle = findViewById(R.id.txtTitle);
        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnDatabase = findViewById(R.id.btnDatabase);
        RecyclerView recycler = findViewById(R.id.recycler);

        //recyclerView layoutManager
        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(lm);
        Context currentContext = this;
        //search process
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sSearch = edtSearch.getText().toString();

                //internet connection
                String url = "https://www.omdbapi.com/?s=" + sSearch + "&apikey=ac214513";
                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Gson gson = new Gson();
                        movie = gson.fromJson(response.toString(), Movie.class);
                        if (movie.getSearch() == null) {
                            movie.setSearch(new ArrayList<Search>());
                        }
                        ItemAdapter adapter = new ItemAdapter(movie.getSearch(), currentContext);
                        recycler.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        System.out.println(errorResponse);
                        //Log.d("Failed: ", "" + statusCode);
                        //Log.d("Error : ", "" + throwable);
                    }
                });
            }
        });

        //show database
        myDatabase database = new myDatabase(MainActivity.this);
        btnDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayDatabaseActivity.class);
                startActivity(intent);
                //database.showDatabase();
            }
        });


    }
}