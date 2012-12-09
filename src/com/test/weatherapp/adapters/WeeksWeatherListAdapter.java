package com.test.weatherapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/9/12
 */
public class WeeksWeatherListAdapter extends SimpleCursorAdapter {

    public WeeksWeatherListAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }
}
