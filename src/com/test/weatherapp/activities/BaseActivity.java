package com.test.weatherapp.activities;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import com.test.weatherapp.callbacks.WeatherAppBroadcastReceiver;
import com.test.weatherapp.callbacks.WeatherAppServiceCallback;
import com.test.weatherapp.util.WeatherAppConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public abstract class BaseActivity extends Activity {

    public abstract String getTag();
    public abstract WeatherAppServiceCallback getServiceCallback();

    private static final String MARKER_START = "-_-_-_-_-_-_-_-_-_-_-_";
    private static final String MARKER_END = " _-_-_-_-_-_-_-_-_-_-_-";

    private WeatherAppBroadcastReceiver mReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mark("onCreate");
        super.onCreate(savedInstanceState);

        mReceiver = new WeatherAppBroadcastReceiver(getServiceCallback());
        filter = new IntentFilter();
        filter.addAction(WeatherAppConstants.ACTION_PARSE_ERROR);
        filter.addAction(WeatherAppConstants.ACTION_NO_INTERNET_ERROR);
        filter.addAction(WeatherAppConstants.ACTION_BAD_LOCATION_ERROR);
        filter.addAction(WeatherAppConstants.ACTION_WEATHER_DATA_LOADED);
    }

    @Override
    protected void onPause() {
        mark("onPause");
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onStart() {
        mark("onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        mark("onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        mark("onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        mark("onResume");
        super.onResume();
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPostResume() {
        mark("onPostResume");
        super.onPostResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        mark("onSaveInstanceState");
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mark("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mark("onDestroy");
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    /**
     * Util method for logging
     * @param msg
     */
    private void mark(String msg) {
        Log.d(getTag(), MARKER_START + msg + MARKER_END);
    }
}
