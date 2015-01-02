package com.grevu.map.MapUser;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by jason on 14. 11. 20..
 */
public class UserListAdapter extends ArrayAdapter<UserInfo> {

    private Context context;

    public UserListAdapter(Context context, int layoutId, ArrayList<UserInfo> userInfoArrayList) {
        super(context, layoutId, userInfoArrayList);

        this.context = context;
    }
}
