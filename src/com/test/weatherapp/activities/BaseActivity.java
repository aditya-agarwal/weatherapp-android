package com.test.weatherapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public abstract class BaseActivity extends Activity {

    public abstract String getTag();

    private static final String MARKER_START = "-_-_-_-_-_-_-_-_-_-_-_";
    private static final String MARKER_END = " _-_-_-_-_-_-_-_-_-_-_-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mark("onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        mark("onPause");
        super.onPause();
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
    }

    /**
     * Util method for logging
     * @param msg
     */
    private void mark(String msg) {
        Log.d(getTag(), MARKER_START + msg + MARKER_END);
    }
}
