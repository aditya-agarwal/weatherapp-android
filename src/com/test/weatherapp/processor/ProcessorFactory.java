package com.test.weatherapp.processor;

import com.test.weatherapp.util.WeatherAppConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class ProcessorFactory {

    public static Processor createProcessor(String action){

        if(action.equals(WeatherAppConstants.ACTION_WEEKS_WEATHER)){
            return new WeeksWeatherProcessor();
        } else if(action.equals(WeatherAppConstants.ACTION_TODAYS_WEATHER)) {
            return new TodaysWeatherProcessor();
        }
        return null;
    }
}
