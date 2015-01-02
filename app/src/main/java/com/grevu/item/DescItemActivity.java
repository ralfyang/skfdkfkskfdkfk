package com.grevu.item;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.grevu.app.R;
import com.grevu.view.CustomEditTextDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 아이템 상세 화면
 * Created by jhkim on 2014-10-27.
 */
public class DescItemActivity extends Activity {

    private ScrollView menuLayout;
    private ImageView mMenuImg1, mMenuImg2;
    private TextView mBtnPay;
    private CustomEditTextDialog mCustomEditTextDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_desc_item);

        menuLayout = (ScrollView) findViewById(R.id.menu_food);

        mMenuImg1 = (ImageView) menuLayout.findViewById(R.id.image_menu1);
        mMenuImg2 = (ImageView) menuLayout.findViewById(R.id.image_menu2);

        mBtnPay = (TextView) findViewById(R.id.btn_pay);

        ImageLoader.getInstance().displayImage("http://121.189.39.226/source_food_01.jpg", mMenuImg1);
        ImageLoader.getInstance().displayImage("http://121.189.39.226/source_food_02.jpg", mMenuImg2);

        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                mCustomEditTextDialog = new CustomEditTextDialog(DescItemActivity.this,
                        "결제 비밀번호",
                        leftClickListener,
                        rightClickListener);

                mCustomEditTextDialog.show();
            }
        });
    }

    private View.OnClickListener leftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCustomEditTextDialog.dismiss();
        }
    };

    private View.OnClickListener rightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCustomEditTextDialog.dismiss();
        }
    };

}
