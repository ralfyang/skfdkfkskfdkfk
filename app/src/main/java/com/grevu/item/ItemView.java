package com.grevu.item;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * 단순 카드 형태의 item
 * Created by jhkim on 2014-10-27.
 */
public class ItemView extends LinearLayout {

    public ItemView(Context context) {
        super(context);
        init();
    }

    public ItemView(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemView(android.content.Context context, android.util.AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {


    }

}
