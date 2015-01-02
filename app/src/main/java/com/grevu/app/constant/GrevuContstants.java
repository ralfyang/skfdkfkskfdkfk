package com.grevu.app.constant;

import android.graphics.Color;

/**
 * Created by jason on 2014. 10. 13..
 */
public class GrevuContstants {
    public static final String TAG = "grevu";
    public static final String DAUM_API_KEY = "4aa10a58d5d9a28801d5ecd284a8f0d817f07ad6";

    public static final boolean DEBUGGABLE_LOG_ENABLED = true;

    public static final int POLYLINE_TAG = 1000;
    public static final int CIRCLE_TAG = 1001;
    public static final int MAPPOINT_TAG = 1002;

    //default zoom level
    public static final int ZOOM_LEVEL = 1;

    public static final class GREVU_CIRCLE {
        public static final int getCircleLineColor() {
            return Color.argb(128, 0, 128, 0);
        }

        public static final int getCircleFillColor() {
            return Color.argb(55, 128, 0, 0);
        }

        public static final int RADIUS = 500;
    }

    public static final class SERVER_INFO {
        public static final String DOMAIN = "http://121.189.39.226";
        public static final String URI_LOCATION_INFO = "/data/location_info.txt";
    }
}
