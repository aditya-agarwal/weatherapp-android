package com.test.weatherapp.callbacks;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/9/12
 */
public interface WeatherAppServiceCallback {
    public void onError(String msg);
    public void onComplete();
}
