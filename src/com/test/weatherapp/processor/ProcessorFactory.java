package com.test.weatherapp.processor;

import android.content.Context;
import com.test.weatherapp.util.WeatherAppConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 *
 * Factory class responsible for creating the processor based on Action
 */
public class ProcessorFactory {

    /**
     * Creates and returns a processor based on action
     * @param action
     * @param context
     * @return Processor
     */
    public static Processor createProcessor(String action, Context context){

        if(action.equals(WeatherAppConstants.ACTION_WEEKS_WEATHER)){
            return new WeeksWeatherProcessor(context);
        } else if(action.equals(WeatherAppConstants.ACTION_TODAYS_WEATHER)) {
            return new TodaysWeatherProcessor(context);
        }
        return null;
    }
}
