package com.test.weatherapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.test.weatherapp.R;
import com.test.weatherapp.database.WeatherAppDBContract;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/9/12
 */
public class WeeksWeatherListAdapter extends CursorAdapter {

    private LayoutInflater mInflater;

    public WeeksWeatherListAdapter(Context context, Cursor c) {
        super(context, c);
        mInflater=LayoutInflater.from(context);
    }

    public WeeksWeatherListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view=mInflater.inflate(R.layout.weeks_weather_list_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String temperature = cursor.getString(cursor.getColumnIndex(WeatherAppDBContract.Weather.COLUMN_NAME_TEMPERATURE));
        String date = cursor.getString(cursor.getColumnIndex(WeatherAppDBContract.Weather.COLUMN_NAME_DATE));
        String desc = cursor.getString(cursor.getColumnIndex(WeatherAppDBContract.Weather.COLUMN_NAME_DESCRIPTION));
        String precip = cursor.getString(cursor.getColumnIndex(WeatherAppDBContract.Weather.COLUMN_NAME_PRECIPITATION));

        TextView temp_tv = (TextView) view.findViewById(R.id.temperature_val);
        temp_tv.setText(temperature);
        TextView date_tv = (TextView) view.findViewById(R.id.date_val);
        date_tv.setText(date);
        TextView desc_tv = (TextView) view.findViewById(R.id.desc_val);
        desc_tv.setText(desc);
        TextView precip_tv = (TextView) view.findViewById(R.id.precip_val);
        precip_tv.setText(precip);

    }
}
