package com.grevu.map;

import android.app.Activity;

import com.grevu.app.constant.GrevuContstants;

import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

/**
 * Created by jason on 14. 10. 23..
 */
public class GrevuMapUtil {

    public static MapPOIItem setCurrentPositionByMarker(MapPoint mapPoint) throws RuntimeException, NullPointerException {
        MapPOIItem poiItem = new MapPOIItem();

        poiItem.setItemName("End point");
        poiItem.setTag(999);
        poiItem.setMapPoint(mapPoint);
        poiItem.setMarkerType(MapPOIItem.MarkerType.BluePin);
        poiItem.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        return poiItem;
    }

    /**
     * Make a Circle
     * */
    public static MapCircle createMapCircle(MapPoint mapPoint) {

        MapCircle mapCircle = new MapCircle(mapPoint, GrevuContstants.GREVU_CIRCLE.RADIUS, GrevuContstants.GREVU_CIRCLE.getCircleLineColor(), GrevuContstants.GREVU_CIRCLE.getCircleFillColor());

        mapCircle.setTag(GrevuContstants.CIRCLE_TAG);
        return mapCircle;
    }

    public static void movetoCurrentLocation(MapView mapView) {
        if (mapView == null) throw new NullPointerException();

        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
    }

    public static void findGrevuReverseGeocode(MapPoint mapPoint, MapReverseGeoCoder.ReverseGeoCodingResultListener reverseGeoCodingResultListener, Activity activity) {
        MapReverseGeoCoder reverseGeoCoder = new MapReverseGeoCoder("LOCAL_API_KEY", mapPoint, reverseGeoCodingResultListener, activity);
        reverseGeoCoder.startFindingAddress();
    }
 }
