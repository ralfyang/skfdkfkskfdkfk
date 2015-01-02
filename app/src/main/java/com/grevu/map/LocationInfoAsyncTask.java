package com.grevu.map;

import android.content.Context;
import android.os.AsyncTask;

import com.grevu.app.data.PoiData;
import com.grevu.app.util.HttpNetworkUtil;
import com.grevu.app.util.HttpResult;
import com.grevu.app.util.JsonUtil;
import com.grevu.app.util.Logger;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 14. 11. 7..
 */
public class LocationInfoAsyncTask extends AsyncTask<Object, Integer, PoiData[]> {

    Context context;

    OnLocationInfoListener onLocationInfoListener;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setOnLocationInfoListener(OnLocationInfoListener onLocationInfoListener) {
        this.onLocationInfoListener = onLocationInfoListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected PoiData[] doInBackground(Object... params) {

        HttpResult result = HttpNetworkUtil.sendHttpPost((String)params[1]);

        if (result.getStatus() == HttpStatus.SC_OK || result.getStatus() == HttpStatus.SC_NOT_MODIFIED) {
            String curResponseMsg = result.getResponseMsg();

            try {
                PoiData[] poiDataArr = JsonUtil.parsePoiItem((String)params[0], curResponseMsg);

                return poiDataArr;
            } catch (Exception e) {
                Logger.e("[LocationInfoAsyncTask] error occured : " + e.getMessage());

                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(PoiData[] poiArr) {
        super.onPostExecute(poiArr);

        if (onLocationInfoListener != null) {
            //MapPOIItem[] mapPOIItems = new MapPOIItem[poiArr.length];

            List<MapPOIItem> poiItemList = new ArrayList<MapPOIItem>();

            for (PoiData poiData : poiArr) {
                MapPOIItem mapPOIItem = new MapPOIItem();

                mapPOIItem.setItemName(poiData.storeName);
                mapPOIItem.setTag(poiData.storeTag);
                mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(poiData.storeLatitude, poiData.storeLongitude));
                mapPOIItem.setMarkerType(MapPOIItem.MarkerType.YellowPin);
                mapPOIItem.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

                poiItemList.add(mapPOIItem);
            }

            onLocationInfoListener.onLocationInfoReceived(poiItemList);
        }
    }
}
