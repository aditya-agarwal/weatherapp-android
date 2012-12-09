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

    private static final String CONTENT_AUTHORITY = "com.orbitz.weatherapp.provider";
    private static final String WEATHER_BASE_PATH = "weather";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(CONTENT_AUTHORITY, WEATHER_BASE_PATH, WEEKLY_WEATHER);
        sURIMatcher.addURI(CONTENT_AUTHORITY, WEATHER_BASE_PATH + "/#", TODAYS_WEATHER);
    }

    private WeatherAppDBHelper mDBHelper;

    @Override
    public boolean onCreate() {
        mDBHelper = new WeatherAppDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getType(Uri uri) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        int uriType = sURIMatcher.match(uri);
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
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
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
