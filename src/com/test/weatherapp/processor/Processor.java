package com.test.weatherapp.processor;

import com.test.weatherapp.util.WeatherAppConstants;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public abstract class Processor {
    public abstract void getWeather(String location);

    protected String createUrl(String location, String num_of_days){
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        if(!location.equals(" ") && location !=null){
            params.add(new BasicNameValuePair(WeatherAppConstants.URL_LOCATION_ATTR, location));
            params.add(new BasicNameValuePair(WeatherAppConstants.URL_FORMAT_ATTR, WeatherAppConstants.FORMAT));
            params.add(new BasicNameValuePair(WeatherAppConstants.URL_NUM_OF_DAYS_ATTR, num_of_days));
            params.add(new BasicNameValuePair(WeatherAppConstants.URL_KEY_ATTR, WeatherAppConstants.API_KEY));
        }

        String paramString = URLEncodedUtils.format(params, "utf-8");
        return WeatherAppConstants.BASE_URL+paramString;
    }
}
