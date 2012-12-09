package com.test.weatherapp.activities;

import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import com.test.weatherapp.R;
import com.test.weatherapp.adapters.WeeksWeatherListAdapter;
import com.test.weatherapp.callbacks.WeatherAppBroadcastReceiver;
import com.test.weatherapp.callbacks.WeatherAppServiceCallback;
import com.test.weatherapp.database.WeatherAppDBContract;
import com.test.weatherapp.service.WeatherAppServiceHelper;
import com.test.weatherapp.util.WeatherAppConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/9/12
 */
public class WeeksWeatherActivity extends BaseActivity implements WeatherAppServiceCallback {

    private static final String LOG_TAG = "WeeksWeatherActivity";
    private String location;

    private WeatherAppBroadcastReceiver mReceiver;


    @Override
    public String getTag() {
        return LOG_TAG;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weeks_weather);

        mReceiver = new WeatherAppBroadcastReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(WeatherAppConstants.ACTION_PARSE_ERROR);
        filter.addAction(WeatherAppConstants.ACTION_NO_INTERNET_ERROR);
        filter.addAction(WeatherAppConstants.ACTION_BAD_LOCATION_ERROR);
        filter.addAction(WeatherAppConstants.ACTION_WEATHER_DATA_LOADED);
        registerReceiver(mReceiver, filter);

        Bundle extras = getIntent().getExtras();

        if(extras !=null)
        {
            location = extras.getString(WeatherAppConstants.EXTRAS_LOCATION);
        }

        WeatherAppServiceHelper.getInstance(this).getWeeklyWeather(location);

    }

    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onComplete() {

        String [] projection = new String[]{
                WeatherAppDBContract.Weather._ID,
                WeatherAppDBContract.Weather.COLUMN_NAME_TEMPERATURE,
                WeatherAppDBContract.Weather.COLUMN_NAME_DATE,
                WeatherAppDBContract.Weather.COLUMN_NAME_DESCRIPTION,
                WeatherAppDBContract.Weather.COLUMN_NAME_PRECIPITATION
        };

        Cursor cursor = getContentResolver().query(WeatherAppDBContract.Weather.CONTENT_URI, projection, null, null, null);
        ListView weeksWeatherList = (ListView)findViewById(R.id.weeks_weather_list_view);

        WeeksWeatherListAdapter adapter = new WeeksWeatherListAdapter(this,cursor);
        weeksWeatherList.setAdapter(adapter);

    }
}
