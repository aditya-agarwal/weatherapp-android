package com.test.weatherapp.util;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class WeatherAppConstants {

    //Actions
    public static final String ACTION_TODAYS_WEATHER ="com.test.weatherapp.TODAYS_WEATHER";
    public static final String ACTION_WEEKS_WEATHER ="com.test.weatherapp.WEEKS_WEATHER";
    public static final String ACTION_PARSE_ERROR = "com.test.weatherapp.PARSE_ERROR";
    public static final String ACTION_NO_INTERNET_ERROR = "com.test.weatherapp.NO_INTERNET_ERROR";


    //Weather Api
    public static final String API_KEY = "223f76cff5164333120812";
    public static final String FORMAT = "json";

    public static final String URL_LOCATION_ATTR = "q";
    public static final String URL_FORMAT_ATTR = "format";
    public static final String URL_NUM_OF_DAYS_ATTR = "num_of_days";
    public static final String URL_KEY_ATTR = "key";
    public static final String BASE_URL = "http://free.worldweatheronline.com/feed/weather.ashx?";


    //JSON TAGS
    public static final String TAG_DATA = "data";
    public static final String TAG_WEATHER = "weather";
    public static final String TAG_DATE = "date";
    public static final String TAG_PRECIP = "precipMM";
    public static final String TAG_TEMP_MAX = "tempMaxF";
    public static final String TAG_TEMP_MIN = "tempMinF";

    public static final String TAG_WEATHER_DESC = "weatherDesc";
    public static final String TAG_VALUE = "value";
    public static final String TAG_ICON_URL = "weatherIconUrl";




}
