package com.grevu.app;

import android.app.Application;
import android.content.res.Configuration;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by jhkim on 14. 10. 22..
 */
public class GravuApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheSize(2 * 1024 * 1024)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheSize(2 * 1024 * 1024)
                .discCacheFileCount(100)
                .writeDebugLogs()  // 릴리즈 시, 삭제
                .build();
        ImageLoader.getInstance().init(config);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
