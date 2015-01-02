package com.grevu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * 인트로화면 백그라운드 스크롤 애미메이션 적용
 * Created by jhkim on 2014. 10. 9..
 */
public class IntroBackgroundView extends View {

    private final int DEFAULT_PADDING = 10;
    private Display mDisplay;
    private Bitmap mImage;

    private float mTotalX = 0;
    private float mTotalY = 0;

    int mPadding;

    public IntroBackgroundView(Context context) {
        super(context);
        initScrollImageView(context);
    }

    private void initScrollImageView(Context context) {
        mDisplay = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mPadding = DEFAULT_PADDING;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDim(widthMeasureSpec, mDisplay.getWidth());
//        int height = measureDim(heightMeasureSpec, mDisplay.getHeight());
        int height = 3000;
        setMeasuredDimension(width, height);

    }

    private int measureDim(int measureSpec, int size) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = size;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mImage == null) {
            return;
        }

        Paint paint = new Paint();
        canvas.drawBitmap(mImage, mTotalX, mTotalY, paint);
    }
}
