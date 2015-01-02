package com.grevu.app.util;

import com.grevu.app.data.PoiData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14. 11. 6..
 */
public class JsonUtil {
    /**
     * parse json array of Poi Items
     * @param json
     * */
    public static PoiData[] parsePoiItem(String selectedCategory, String json) throws JSONException {
        JSONObject poiResult = new JSONObject(json);

        JSONArray jsonArray = poiResult.getJSONArray("cateList");   //new JSONArray(json);

        for (int i=0;i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            //Category 확인
            String storeCate = jsonObject.getString("storeCate");

            if (selectedCategory.equals(storeCate)) {

                JSONArray storeList = jsonObject.getJSONArray("storeList");

                PoiData[] poiDataArr = new PoiData[storeList.length()];

                for (int j=0; j < storeList.length(); j++) {
                    PoiData curPoiData = new PoiData();

                    JSONObject storeObject = storeList.getJSONObject(j);

                    String storeName = storeObject.getString("storeName");
                    int storeTag = storeObject.getInt("storeTag");
                    double latitude = storeObject.getDouble("latitude");
                    double longitude = storeObject.getDouble("longitude");

                    curPoiData.storeName = storeName;
                    curPoiData.storeTag = storeTag;
                    curPoiData.storeLatitude = latitude;
                    curPoiData.storeLongitude = longitude;

                    Logger.d("storeName : " + curPoiData.storeName + ", storeTag : " + curPoiData.storeTag + ", latitude : " + curPoiData.storeLatitude + ", longitude : " + curPoiData.storeLongitude);
                    poiDataArr[j] = curPoiData;
                }

                return poiDataArr;
            }
        }
        return null;
    }
}
