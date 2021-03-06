package com.test.weatherapp.processor;

import android.content.Context;
import com.test.weatherapp.http.HttpHandler;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 *
 * Processor responsible for getting weeks weathers
 */
public class WeeksWeatherProcessor extends Processor {

    protected WeeksWeatherProcessor(Context context) {
        super(context);
    }

    /**
     * Makes Http request to get weeks weather -- parse and stores the result
     * @param location String location in zip code or city/town
     */
    @Override
    public void getWeather(String location) {
        HttpHandler httpHandler = new HttpHandler();
        JSONObject json = httpHandler.request(createUrl(location, "5"));
        parse(json);
    }
}
