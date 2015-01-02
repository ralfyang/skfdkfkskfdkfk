package com.grevu.map;

import com.grevu.app.data.PoiData;

import net.daum.mf.map.api.MapPOIItem;

import java.util.List;

/**
 * Created by jason on 14. 11. 7..
 */
public interface OnLocationInfoListener {
    void onLocationInfoReceived(List<MapPOIItem> itemList);
}
