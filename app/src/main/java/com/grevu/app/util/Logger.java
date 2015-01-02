package com.grevu.app.util;

import com.grevu.app.constant.GrevuContstants;

/**
 * Created by jason on 2014. 10. 13..
 */
public class Logger {
    /**
     * Information Log
     *
     * @param msg
     */
    public static void i(String msg) {

        android.util.Log.i(GrevuContstants.TAG, msg);
    }

    /**
     * Error Log
     *
     * @param msg
     */
    public static void e(String msg) {

        android.util.Log.e(GrevuContstants.TAG, msg);
    }

    /**
     * Debug Log
     *
     * @param msg
     */
    public static void d(String msg) {

        if (GrevuContstants.DEBUGGABLE_LOG_ENABLED)
            android.util.Log.d(GrevuContstants.TAG, msg);
    }
}
