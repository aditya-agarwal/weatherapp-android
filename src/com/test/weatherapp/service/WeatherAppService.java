package com.test.weatherapp.service;

import android.app.IntentService;
import android.content.Intent;
import com.test.weatherapp.processor.ProcessorFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class WeatherAppService extends IntentService {

    public WeatherAppService() {
        super("WeatherAppService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getStringExtra("action");
        ProcessorFactory.createProcessor(action).getWeather();

        //TODO: Broadcast intent
    }
}
