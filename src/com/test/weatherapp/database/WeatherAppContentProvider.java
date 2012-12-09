package com.test.weatherapp.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class WeatherAppContentProvider extends ContentProvider {

    private static final int TODAYS_WEATHER = 10;
    private static final int WEEKLY_WEATHER = 20;

    public static final String CONTENT_AUTHORITY = "com.test.weatherapp.provider";
    public static final String WEATHER_BASE_PATH = "weather";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(CONTENT_AUTHORITY, WEATHER_BASE_PATH, WEEKLY_WEATHER);
        sURIMatcher.addURI(CONTENT_AUTHORITY, WEATHER_BASE_PATH + "/#", TODAYS_WEATHER);
    }

    private WeatherAppDBHelper mDBHelper;

    public static final String SQL_CREATE_TABLE_WEATHER = " CREATE TABLE IF NOT EXISTS " + WeatherAppDBContract.Weather.TABLE_NAME +
            " (" +
            WeatherAppDBContract.Weather._ID + " INTEGER PRIMARY KEY," +
            WeatherAppDBContract.Weather.COLUMN_NAME_DATE + " TEXT NOT NULL," +
            WeatherAppDBContract.Weather.COLUMN_NAME_LOCATION + " TEXT NOT NULL," +
            WeatherAppDBContract.Weather.COLUMN_NAME_TEMPERATURE + " INTEGER NOT NULL," +
            WeatherAppDBContract.Weather.COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL," +
            WeatherAppDBContract.Weather.COLUMN_NAME_ICON_URL + " TEXT NOT NULL," +
            WeatherAppDBContract.Weather.COLUMN_NAME_PRECIPITATION + " INTEGER NOT NULL"
            + " )" ;

    @Override
    public boolean onCreate() {
        mDBHelper = new WeatherAppDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        int uriType = sURIMatcher.match(uri);
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(SQL_CREATE_TABLE_WEATHER);
        long id = 0;
        switch (uriType) {
            //TODO : COMBINE CASES BELOW
            case WEEKLY_WEATHER:
                id = db.insert(WeatherAppDBContract.Weather.TABLE_NAME, null, contentValues);
                break;
            case TODAYS_WEATHER:
                id = db.insert(WeatherAppDBContract.Weather.TABLE_NAME, null, contentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(WEATHER_BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + WeatherAppDBContract.Weather.TABLE_NAME);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
