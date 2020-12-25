package com.sanaz.iaust.hotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

public class myDatabase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Netflix";
    private static final String COL_ID = "_id";
    private static final String COL_TITLE = "title";
    private static final String COL_TYPE = "type";
    private static final String COL_YEAR = "year";
    String TABLE_NAME = "movies";

    String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT, "
            + COL_TYPE + " TEXT, "
            + COL_YEAR + " INTEGER"
            + ")";

    public myDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_QUERY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertMovie(String title, String type, int year) {

        String INSERT_MOVIE_QUERY = "INSERT INTO " + TABLE_NAME + "(title, type ,year) VALUES ("
                + " ' " + title + " ' " + "," +
                " ' " + type + " ' " + "," +
                year + ")";

        SQLiteDatabase sqlitedatabase = this.getWritableDatabase();
        sqlitedatabase.execSQL(INSERT_MOVIE_QUERY);
        sqlitedatabase.close();
    }

    public Cursor showDatabase() {
        String SELECTION_QUERY = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqlitedatabase = this.getReadableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery(SELECTION_QUERY, null);
        //sqlitedatabase.close();
        return cursor;
    }


}
