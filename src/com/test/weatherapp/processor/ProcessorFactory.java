package com.test.weatherapp.processor;

import android.content.Context;
import com.test.weatherapp.util.WeatherAppConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class ProcessorFactory {

    public static Processor createProcessor(String action, Context context){

        if(action.equals(WeatherAppConstants.ACTION_WEEKS_WEATHER)){
            return new WeeksWeatherProcessor(context);
        } else if(action.equals(WeatherAppConstants.ACTION_TODAYS_WEATHER)) {
            return new TodaysWeatherProcessor(context);
        }
        return null;
    }
}
