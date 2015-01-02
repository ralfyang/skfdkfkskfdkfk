package com.grevu.item;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * viewpager 형태의 item
 * Created by jhkim on 2014-10-27.
 */
public class ItemViewPager extends ViewPager {

    private static final String TAG = "ItemViewPager";

    private ItemViewPagerAdapter mPagerAdapter;
    private int mListPosition = 0;

    public ItemViewPager(Context context) {
        super(context);
        init(context);
    }

    public ItemViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        mPagerAdapter = new ItemViewPagerAdapter(context);
        setAdapter(mPagerAdapter);
    }

    // list의 position 정보 set,get
    public void setmListPosition(int mListPosition) {
        this.mListPosition = mListPosition;
    }

    public int getmListPosition() {
        return mListPosition;
    }

}
