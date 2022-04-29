package com.xiaozhezhe.widgettest;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class AppWidgetManager {
    private static final String TAG = "AppWidgetManager";
    private static volatile AppWidgetManager sInstance;

    private AppWidgetManager() {

    }

    public static AppWidgetManager getInstance() {
        if (sInstance == null) {
            synchronized (AppWidgetManager.class) {
                if (sInstance == null) {
                    sInstance = new AppWidgetManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * App内部更新App Widget
     */
    public void updateAppWidget(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), NewAppWidget.class);
        intent.setAction(android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = android.appwidget.AppWidgetManager.getInstance(context.getApplicationContext())
                .getAppWidgetIds(new ComponentName(context.getApplicationContext(), NewAppWidget.class));
        intent.putExtra(android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }

    /**
     * 应用内添加App Widget
     *
     * @param context          外部传入ApplicationContext
     * @param isGotoHomeScreen
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addAppWidgetInApp(Context context, boolean isGotoHomeScreen) {
        boolean isSuccess = false;
        try {
            android.appwidget.AppWidgetManager appWidgetManager = context.getSystemService(android.appwidget.AppWidgetManager.class);
            ComponentName snapWidget = new ComponentName(context, NewAppWidget.class);
            if (appWidgetManager.isRequestPinAppWidgetSupported()) {
                // AppWidget创建成功回调
                Intent pinnedWidgetCallbackIntent = new Intent(context, PinnedAppWidgetReceiver.class);
                pinnedWidgetCallbackIntent.putExtra(
                        PinnedAppWidgetReceiver.GO_HOME_SCREEN_PARAMS, isGotoHomeScreen);
                PendingIntent successCallback = PendingIntent.getBroadcast(context, 0,
                        pinnedWidgetCallbackIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                // 如果不需要创建成功回调，requestPinAppWidget的第三个参数可以为null
                isSuccess =
                        appWidgetManager.requestPinAppWidget(snapWidget, null, successCallback);
                Log.d(TAG, "addAppWidgetInApp isSuccess is " + isSuccess);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * 判断是否安装了Widget
     */
    public boolean isAppWidgetInstalled(Context context) {
        if (context == null) {
            return false;
        }
        boolean isInstalled = false;
        ComponentName currentWidget
                = new ComponentName(context.getApplicationContext(), NewAppWidget.class);
        if (currentWidget != null) {
            int[] allWidgetIds = android.appwidget.AppWidgetManager.getInstance(
                    context.getApplicationContext()).getAppWidgetIds(currentWidget);
            isInstalled = allWidgetIds != null && allWidgetIds.length > 0;
        }
        return isInstalled;
    }
}
