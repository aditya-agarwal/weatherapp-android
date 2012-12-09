package com.test.weatherapp.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 12/8/12
 */
public class ToolKit {

    /**
     * Check for internet connectivity
     * @param context
     * @return
     */
    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null)
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        else
            return false;
    }


}
