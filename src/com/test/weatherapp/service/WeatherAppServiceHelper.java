package com.test.weatherapp.service;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.test.weatherapp.R;
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
    public boolean getTodaysWeather(){
        return startService(WeatherAppConstants.ACTION_TODAYS_WEATHER);
    }

    /**
     * Helper method to start service to get weeks weather
     */
    public boolean getWeeklyWeather(){
        return startService(WeatherAppConstants.ACTION_WEEKS_WEATHER);
    }

    private boolean startService(String action){

        if(ToolKit.isInternetAvailable(mContext)){
            intent = new Intent(mContext, WeatherAppService.class);
            intent.putExtra("action", action);
            mContext.startService(intent);
            return true;
        }else {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.toast_message, null);

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(R.string.no_internet_available);

            Toast toast = new Toast(mContext);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

            toast.show();
            return false;
        }
    }

    public void stopService(){
        mContext.stopService(intent);
    }
}
