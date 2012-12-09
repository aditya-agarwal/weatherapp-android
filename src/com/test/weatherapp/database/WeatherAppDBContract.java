package com.test.weatherapp.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 *
 * Contract class to define constants for URIs, tables, and columns
 * Create Inner classes for each table in the db and implement Basecolumns
 */
public class WeatherAppDBContract {

    private WeatherAppDBContract(){}

    /**
     * Inner class defining Database table Weather
     */
    public static class Weather implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.parse("content://" + WeatherAppContentProvider.CONTENT_AUTHORITY
                + "/" + WeatherAppContentProvider.WEATHER_BASE_PATH);
        public static final String TABLE_NAME = "weather";
        // DATE FORMAT YYYY-MM-DD
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TEMPERATURE = "temperature";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_ICON_URL = "icon_url";
        public static final String COLUMN_NAME_PRECIPITATION = "precipitation";

        public static final String SQL_CREATE_TABLE_WEATHER = " CREATE TABLE IF NOT EXISTS " + WeatherAppDBContract.Weather.TABLE_NAME +
                " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_DATE + " TEXT NOT NULL," +
                COLUMN_NAME_TEMPERATURE + " INTEGER NOT NULL," +
                COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL," +
                COLUMN_NAME_ICON_URL + " TEXT NOT NULL," +
                COLUMN_NAME_PRECIPITATION + " INTEGER NOT NULL"
                + " )" ;

        public static final String SQL_DELETE_WEATHER =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
