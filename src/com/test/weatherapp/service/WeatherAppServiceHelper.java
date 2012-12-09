package com.test.weatherapp.service;

import android.content.Context;
import android.content.Intent;
import com.test.weatherapp.util.ToolKit;
import com.test.weatherapp.util.WeatherAppConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 *
 * Handles service to fetch weather
 */
public class WeatherAppServiceHelper {

    private Context mContext;
    private Intent intent;
    private static WeatherAppServiceHelper mServiceHelper;

    private WeatherAppServiceHelper(Context context){
         mContext = context;
    }

    public static WeatherAppServiceHelper getInstance(Context context) {

        if (mServiceHelper == null)
            mServiceHelper = new WeatherAppServiceHelper(context);
        mServiceHelper.setContext(context);

        return mServiceHelper;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * Helper method to start service to get todays weather
     */
    public boolean getTodaysWeather(String location){
        return startService(WeatherAppConstants.ACTION_TODAYS_WEATHER, location);
    }

    /**
     * Helper method to start service to get weeks weather
     */
    public boolean getWeeklyWeather(String location){
        return startService(WeatherAppConstants.ACTION_WEEKS_WEATHER, location);
    }

    private boolean startService(String action, String location){

        if(ToolKit.isInternetAvailable(mContext)){
            intent = new Intent(mContext, WeatherAppService.class);
            intent.putExtra("action", action);
            intent.putExtra("location", location);
            mContext.startService(intent);
            return true;
        }else {
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(WeatherAppConstants.ACTION_NO_INTERNET_ERROR);
            mContext.sendBroadcast(broadcastIntent);
            return false;
        }
    }

    public void stopService(){
        mContext.stopService(intent);
    }
}
