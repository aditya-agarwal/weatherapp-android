package com.test.weatherapp.service;

import android.app.IntentService;
import android.content.Intent;
import com.test.weatherapp.processor.ProcessorFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 *
 * Service to start REST methods to get Weather
 */
public class WeatherAppService extends IntentService {

    public WeatherAppService() {
        super("WeatherAppService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getStringExtra("action");
        String location = intent.getStringExtra("location");
        ProcessorFactory.createProcessor(action, getApplicationContext()).getWeather(location);
    }
}
