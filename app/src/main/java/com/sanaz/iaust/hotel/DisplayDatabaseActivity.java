package com.sanaz.iaust.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayDatabaseActivity extends AppCompatActivity {

    myDatabase mydatabase;
    List<DataList> databaselist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_database);

        databaselist = new ArrayList<DataList>();
        //show database
        mydatabase = new myDatabase(DisplayDatabaseActivity.this);
        Cursor cursor = mydatabase.showDatabase();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            DataList datalist = new DataList();
            datalist.setId(cursor.getInt(0));
            datalist.setTitle(cursor.getString(1));
            databaselist.add(datalist);
        }

        RecyclerView recycler = findViewById(R.id.recycler);

        LinearLayoutManager lm = new LinearLayoutManager(DisplayDatabaseActivity.this, RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(lm);
        DatabaseAdapter adapter = new DatabaseAdapter(databaselist);
        recycler.setAdapter(adapter);

        //get intent from mainActivity
        Intent intent = getIntent();


    }
}