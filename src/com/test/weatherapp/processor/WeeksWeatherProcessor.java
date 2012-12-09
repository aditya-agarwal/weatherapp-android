package com.test.weatherapp.processor;

import android.content.Context;
import com.test.weatherapp.http.HttpHandler;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class WeeksWeatherProcessor extends Processor {

    protected WeeksWeatherProcessor(Context context) {
        super(context);
    }

    @Override
    public void getWeather(String location) {
        HttpHandler httpHandler = new HttpHandler();
        JSONObject json = httpHandler.request(createUrl(location, "5"));
        parse(json);
    }
}
