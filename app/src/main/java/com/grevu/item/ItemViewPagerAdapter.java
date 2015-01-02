package com.grevu.item;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.grevu.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by jhkim on 2014-10-27.
 */
public class ItemViewPagerAdapter extends PagerAdapter {

    private static final String TAG = "ItemViewPagerAdapter";
    private final String[] sDrawableUrls = {"http://121.189.39.226/store_list_sample_01.png", "http://121.189.39.226/store_list_sample_02.png"
            , "http://121.189.39.226/store_list_sample_03.png", "http://121.189.39.226/store_list_sample_04.png", "http://121.189.39.226/store_list_sample_05.png"
            , "http://121.189.39.226/store_list_sample_06.png", "http://121.189.39.226/store_list_sample_07.png", "http://121.189.39.226/store_list_sample_08.png"};

    private final String[] sNames = {"양남식육식당", "엘파소", "숯불한판", "고기야", "비어뱅뱅", "너도꼼장어", "Coffee Smite", "아우네 김치찌개"};
    private final String[] sDists = {"4M", "6M", "7M", "10M", "10M", "11M", "12M", "15M"};

    private Context mContext;
    private LayoutInflater mInflater;
    private DisplayImageOptions mOptions;

    ItemViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);

        mOptions = new DisplayImageOptions.Builder()
//                .showImageForEmptyUri(R.drawable.ic_empty)
//                .showImageOnFail(R.drawable.ic_error)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return sDrawableUrls.length;
    }

    @Override
    public Object instantiateItem(final ViewGroup view, int position) {
        FrameLayout imageLayout = (FrameLayout) mInflater.inflate(R.layout.item_pager_image, view, false);
        assert imageLayout != null;
        final ImageButton pickFood = (ImageButton) imageLayout.findViewById(R.id.pick_food);
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        TextView storeName = (TextView) imageLayout.findViewById(R.id.store_name);
        TextView storeDist = (TextView) imageLayout.findViewById(R.id.store_distance);

        final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);

        ImageLoader.getInstance().displayImage(sDrawableUrls[position], imageView, mOptions, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                spinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message = null;
                switch (failReason.getType()) {
                    case IO_ERROR:
                        message = "Input/Output error";
                        break;
                    case DECODING_ERROR:
                        message = "Image can't be decoded";
                        break;
                    case NETWORK_DENIED:
                        message = "Downloads are denied";
                        break;
                    case OUT_OF_MEMORY:
                        message = "Out Of Memory error";
                        break;
                    case UNKNOWN:
                        message = "Unknown error";
                        break;
                }
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                spinner.setVisibility(View.GONE);
            }
        });

        final int storeIndex = ((ItemViewPager)view).getmListPosition();

        storeName.setText(sNames[storeIndex]);
        storeDist.setText(sDists[storeIndex]);

        pickFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected()){
                    v.setSelected(false);
                }else{
                    v.setSelected(true);
                }

                v.setBackgroundResource(R.drawable.pickonoff);
            }
        });

        view.addView(imageLayout, 0);

        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DescItemActivity.class);
                //container(ItemViewPager)에서 listPosition get
                intent.putExtra("index", String.valueOf(storeIndex));
                mContext.startActivity(intent);
            }
        });
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
