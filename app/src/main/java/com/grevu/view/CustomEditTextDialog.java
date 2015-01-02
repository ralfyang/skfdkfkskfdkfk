package com.grevu.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.grevu.app.R;

/**
 * Created by jhkim on 2014-11-12.
 */
public class CustomEditTextDialog extends Dialog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.layout_popup_edittext);

        setLayout();
        setTitle(mTitle);
        setClickListener(mLeftClickListener , mRightClickListener);

        EditText password = (EditText) findViewById(R.id.popup_edittext);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
    }

    public CustomEditTextDialog(Context context) {
        // Dialog 배경을 투명 처리 해준다.
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
    }

    public CustomEditTextDialog(Context context, String title,
                                View.OnClickListener singleListener) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mLeftClickListener = singleListener;
    }

    public CustomEditTextDialog(Context context, String title,
                                View.OnClickListener leftListener, View.OnClickListener rightListener) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;

    }

    private void setTitle(String title){
        mTitleView.setText(title);
    }


    private void setClickListener(View.OnClickListener left , View.OnClickListener right){
        if(left!=null && right!=null){
            mLeftButton.setOnClickListener(left);
            mRightButton.setOnClickListener(right);
        }else if(left!=null && right==null){
            mLeftButton.setOnClickListener(left);
        }else {

        }
    }

    private TextView mTitleView;
    private EditText mEditText;
    private Button mLeftButton;
    private Button mRightButton;
    private String mTitle;

    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    /*
     * Layout
     */
    private void setLayout(){
        mTitleView = (TextView) findViewById(R.id.popup_title);
        mEditText = (EditText) findViewById(R.id.popup_edittext);
        mLeftButton = (Button) findViewById(R.id.bt_left);
        mRightButton = (Button) findViewById(R.id.bt_right);
    }

}
