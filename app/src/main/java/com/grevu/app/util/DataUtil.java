package com.grevu.app.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jhkim on 2014. 10. 15..
 */
public class DataUtil {

    public static final String IMAGE_URL = "http://121.189.39.226/";

    public static String getPreferences(Activity activity, String name) {
        SharedPreferences pref = activity.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getString(name, "");
    }

    public static void setPreferences(Activity activity, String name, String value) {
        SharedPreferences pref = activity.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, value);
        editor.commit();
    }
}
