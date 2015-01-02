package com.grevu.app.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jason on 14. 10. 27..
 */
public class CommonUtil {

    public static void showToastByString(Context context, String name) throws Exception {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }

    public static void showToastById(Context context, int id) throws Exception {
        Toast.makeText(context, context.getString(id), Toast.LENGTH_SHORT).show();
    }
}
