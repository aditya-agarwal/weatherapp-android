package com.test.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class WeatherAppDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Weather.db";

    private static final String SQL_CREATE_TABLE_WEATHER = " CREATE TABLE " + WeatherAppDBContract.Weather.TABLE_NAME +
            " (" +
            WeatherAppDBContract.Weather._ID + " INTEGER PRIMARY KEY," +
            WeatherAppDBContract.Weather.COLUMN_NAME_DATE + " TEXT NOT NULL" +
            WeatherAppDBContract.Weather.COLUMN_NAME_LOCATION + " TEXT NOT NULL" +
            WeatherAppDBContract.Weather.COLUMN_NAME_TEMPERATURE + " INTEGER NOT NULL" +
            WeatherAppDBContract.Weather.COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL" +
            WeatherAppDBContract.Weather.COLUMN_NAME_ICON_URL + " TEXT NOT NULL" +
            WeatherAppDBContract.Weather.COLUMN_NAME_PRECIPITATION + " INTEGER NOT NULL"
            + " )" ;

    private static final String SQL_DELETE_WEATHER =
            "DROP TABLE IF EXISTS " + WeatherAppDBContract.Weather.TABLE_NAME;

    public WeatherAppDBHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_WEATHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_WEATHER);
        onCreate(db);
    }
}
