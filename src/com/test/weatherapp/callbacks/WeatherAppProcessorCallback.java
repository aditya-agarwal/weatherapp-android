package com.test.weatherapp.callbacks;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/9/12
 *
 * Interface definitions for Processor callbacks
 */
public interface WeatherAppProcessorCallback {

    /**
     * This method is called from broadcast receiver in case of an error
     * @param msg
     */
    public void onError(String msg);

    /**
     * This method is called from broadcast receive when data has been loaded in sqlite database
     */
    public void onComplete();
}
