package com.test.weatherapp.processor;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import com.test.weatherapp.database.WeatherAppDBContract;
import com.test.weatherapp.util.WeatherAppConstants;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public abstract class Processor {

    private Context mContext;
    public abstract void getWeather(String location);

    protected Processor(Context context){
        mContext = context;
    }

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

    public void parse(JSONObject object) {
        try {
            //Delete previously stored values
            mContext.getContentResolver().delete(WeatherAppDBContract.Weather.CONTENT_URI, null,null);
            JSONObject data = object.getJSONObject(WeatherAppConstants.TAG_DATA);
            JSONArray weather = data.getJSONArray(WeatherAppConstants.TAG_WEATHER);

            for(int i = 0; i < weather.length(); i++){
                JSONObject weatherObj = weather.getJSONObject(i);

                String date = weatherObj.getString(WeatherAppConstants.TAG_DATE);
                String tempMax = weatherObj.getString(WeatherAppConstants.TAG_TEMP_MAX);
                String tempMin = weatherObj.getString(WeatherAppConstants.TAG_TEMP_MIN);
                String precip = weatherObj.getString(WeatherAppConstants.TAG_PRECIP);

                //Description
                JSONArray descArr = weatherObj.getJSONArray(WeatherAppConstants.TAG_WEATHER_DESC);
                JSONObject descObj = descArr.getJSONObject(0);
                String description = descObj.getString(WeatherAppConstants.TAG_VALUE);

                //Icon URL
                JSONArray urlArr = weatherObj.getJSONArray(WeatherAppConstants.TAG_ICON_URL);
                JSONObject urlObj = urlArr.getJSONObject(0);
                String iconUrl = urlObj.getString(WeatherAppConstants.TAG_VALUE);

                ContentValues value = new ContentValues();
                value.put(WeatherAppDBContract.Weather.COLUMN_NAME_DATE, date);
                value.put(WeatherAppDBContract.Weather.COLUMN_NAME_TEMPERATURE, tempMax);
                value.put(WeatherAppDBContract.Weather.COLUMN_NAME_PRECIPITATION, precip);
                value.put(WeatherAppDBContract.Weather.COLUMN_NAME_ICON_URL, iconUrl);
                value.put(WeatherAppDBContract.Weather.COLUMN_NAME_DESCRIPTION, description);
                mContext.getContentResolver().insert(WeatherAppDBContract.Weather.CONTENT_URI, value);
            }
        } catch (JSONException e) {
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(WeatherAppConstants.ACTION_PARSE_ERROR);
            mContext.sendBroadcast(broadcastIntent);
            e.printStackTrace();
        }

    }
}
