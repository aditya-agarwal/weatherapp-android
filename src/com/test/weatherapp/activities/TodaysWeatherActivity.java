package com.test.weatherapp.activities;

import android.content.IntentFilter;
import android.os.Bundle;
import com.test.weatherapp.R;
import com.test.weatherapp.callbacks.WeatherAppBroadcastReceiver;
import com.test.weatherapp.service.WeatherAppServiceHelper;
import com.test.weatherapp.util.WeatherAppConstants;

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

        WeatherAppServiceHelper.getInstance(this).getTodaysWeather("Los Angeles");

        registerReceiver(new WeatherAppBroadcastReceiver(),new IntentFilter(WeatherAppConstants.ACTION_PARSE_ERROR));
    }

    @Override
    public void onResume(){
        super.onResume();
        //TODO: UNREGISTER RECEIVER
    }


}
