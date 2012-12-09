package com.test.weatherapp;

import android.os.Bundle;
import com.test.weatherapp.activities.BaseActivity;
import com.test.weatherapp.service.WeatherAppServiceHelper;

public class TodaysWeatherActivity extends BaseActivity {

    private static final String LOG_TAG = "CurrentWeatherActivity";

    @Override
    public String getTag() {
        return LOG_TAG;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        WeatherAppServiceHelper.getInstance(this).getTodaysWeather();
    }
}
