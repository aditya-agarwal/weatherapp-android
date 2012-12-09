package com.test.weatherapp.processor;

import com.test.weatherapp.http.HttpHandler;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class TodaysWeatherProcessor implements Processor {

    @Override
    public void getWeather() {
        HttpHandler httpHandler = new HttpHandler();
        httpHandler.request("http://free.worldweatheronline.com/feed/weather.ashx?q=Farmington+Hills&format=json&num_of_days=2&key=223f76cff5164333120812");
    }
}
