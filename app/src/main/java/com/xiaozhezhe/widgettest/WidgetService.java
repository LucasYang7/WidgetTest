package com.xiaozhezhe.widgettest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.appwidget.AppWidgetManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.AppWidgetTarget;

import java.util.concurrent.ExecutionException;

public class WidgetService extends RemoteViewsService {

    private static final String TAG = WidgetService.class
            .getSimpleName();

    final static int[] mImageIds = {
            R.drawable.video01,
            R.drawable.video02,
            R.drawable.video03,
            R.drawable.video04,
            R.drawable.video05,
            R.drawable.video06,
            R.drawable.video07,
            R.drawable.video08,
            R.drawable.video09,
            R.drawable.video11,
            R.drawable.video12,
            R.drawable.video13,
            R.drawable.video14,
            R.drawable.video15,
            R.drawable.video16,
            R.drawable.video17,
            R.drawable.video18,
            R.drawable.video19,
            R.drawable.video22,
            R.drawable.video23,
            R.drawable.video24,
            R.drawable.video25,
            R.drawable.video26,
            R.drawable.video27,
            R.drawable.video28,
            R.drawable.video29,
            R.drawable.video32,
            R.drawable.video33,
            R.drawable.video34,
            R.drawable.video35,
            R.drawable.video36,
            R.drawable.video37,
            R.drawable.video38,
            R.drawable.video39,
            R.drawable.video42,
            R.drawable.video43,
            R.drawable.video44,
            R.drawable.video45,
            R.drawable.video46,
            R.drawable.video47,
            R.drawable.video48,
            R.drawable.video49,
            R.drawable.video52,
            R.drawable.video53,
            R.drawable.video54,
            R.drawable.video55,
            R.drawable.video56,
            R.drawable.video57,
            R.drawable.video58,
            R.drawable.video59,
            R.drawable.video62,
            R.drawable.video63,
            R.drawable.video66,
            R.drawable.video65,
            R.drawable.video66,
            R.drawable.video67,
            R.drawable.video68,
            R.drawable.video69,
            R.drawable.video72,
            R.drawable.video73,
            R.drawable.video75,
            R.drawable.video77,
            R.drawable.video78,
            R.drawable.video79,
            R.drawable.video82,
            R.drawable.video83,
            R.drawable.video84,
            R.drawable.video85,
            R.drawable.video86,
            R.drawable.video87,
            R.drawable.video88,
            R.drawable.video89
    };

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d(TAG, "onGetViewFactory()");

        return new RemoteViewFactory(this.getApplicationContext(), intent);
    }

    private class RemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

        private int mWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
        private Context mContext;

        public RemoteViewFactory(Context context, Intent intent) {
            mContext = context;
            mWidgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {
            Log.i(TAG, "onCreate()");

        }

        @Override
        public void onDataSetChanged() {
            Log.i(TAG, "onDataSetChanged()");
        }

        @Override
        public void onDestroy() {
            Log.i(TAG, "onDestroy()");
        }

        @Override
        public int getCount() {
            Log.i(TAG, "getCount() " + mImageIds.length);

            return mImageIds.length;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            Log.i(TAG, "getViewAt()" + position);
            @SuppressLint("RemoteViewLayout")
            RemoteViews page = new RemoteViews(mContext.getPackageName(),
                    R.layout.item_widget_image);
            FutureTarget<Bitmap> futureTarget = Glide.with(mContext.getApplicationContext())
                    .asBitmap()
                    .load(mImageIds[position])
                    .transform(new CenterCrop(),new RoundedCorners(20))
                    .submit(600, 600);
            try {
                page.setImageViewBitmap(R.id.widgetImage, futureTarget.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            page.setTextViewText(R.id.tv_widget_item, String.valueOf(position));
            return page;
        }

        @Override
        public RemoteViews getLoadingView() {
            Log.i(TAG, "getLoadingView()");

            return null;
        }

        @Override
        public int getViewTypeCount() {
            Log.i(TAG, "getViewTypeCount()");

            return mImageIds.length;
        }

        @Override
        public long getItemId(int position) {
            Log.i(TAG, "getItemId()");

            return position;
        }

        @Override
        public boolean hasStableIds() {
            Log.i(TAG, "hasStableIds()");

            return true;
        }

    }

}