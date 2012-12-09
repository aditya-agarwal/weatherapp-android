package com.test.weatherapp.callbacks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.test.weatherapp.R;
import com.test.weatherapp.util.ToolKit;
import com.test.weatherapp.util.WeatherAppConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/9/12
 */
public class WeatherAppBroadcastReceiver extends BroadcastReceiver {

    private WeatherAppServiceCallback mCallback;

    public WeatherAppBroadcastReceiver (WeatherAppServiceCallback callback){
         mCallback = callback;
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        //TODO: CHANGE TO HASHMAP
        if(intent.getAction().equals(WeatherAppConstants.ACTION_NO_INTERNET_ERROR)){
            ToolKit.showToastWithError(R.string.no_internet_available,context);
            mCallback.onError(context.getString(R.string.no_internet_available));
        }

        else if(intent.getAction().equals(WeatherAppConstants.ACTION_PARSE_ERROR)){
            ToolKit.showToastWithError(R.string.parsing_error,context);
        }

        else if(intent.getAction().equals(WeatherAppConstants.ACTION_BAD_LOCATION_ERROR)){
            ToolKit.showToastWithError(R.string.bad_location,context);
            mCallback.onError(context.getString(R.string.bad_location));
        }

        else if(intent.getAction().equals(WeatherAppConstants.ACTION_WEATHER_DATA_LOADED)){
            mCallback.onComplete();
        }
    }
}
