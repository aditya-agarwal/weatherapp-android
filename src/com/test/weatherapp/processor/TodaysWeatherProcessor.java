package com.test.weatherapp.processor;

import com.test.weatherapp.http.HttpHandler;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class TodaysWeatherProcessor extends Processor {

    @Override
    public void getWeather(String location) {
        HttpHandler httpHandler = new HttpHandler();
        JSONObject json = httpHandler.request(createUrl(location, "1"));

        //TODO: get json parse it and store in db and update callabck
    }

}
