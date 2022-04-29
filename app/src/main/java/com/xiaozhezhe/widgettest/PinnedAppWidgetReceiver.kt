package com.xiaozhezhe.widgettest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PinnedAppWidgetReceiver : BroadcastReceiver() {

    companion object {
        /**
         * 判断是否需要退到手机桌面
         */
        const val GO_HOME_SCREEN_PARAMS = "go_home_screen_params"
        const val TAG = "PinnedAppWidgetReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Add widget!!!", Toast.LENGTH_SHORT).show()
        val isGotoHomeScreen = intent.getBooleanExtra(GO_HOME_SCREEN_PARAMS, false)
        if (isGotoHomeScreen) {
            val goHomeScreenIntent = Intent().apply {
                action = Intent.ACTION_MAIN
                addCategory(Intent.CATEGORY_DEFAULT)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(goHomeScreenIntent)
        }
    }
}