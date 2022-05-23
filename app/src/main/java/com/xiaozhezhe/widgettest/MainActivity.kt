package com.xiaozhezhe.widgettest

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_add_app_widget.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                AppWidgetManager.getInstance().addAppWidgetInApp(
                    this@MainActivity.applicationContext, false
                )
            }
        }
        val numberImages = intArrayOf(
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
        )
        val adapter = ViewFlipperAdapter(this@MainActivity, numberImages)
        viewFlipper.flipInterval = 42
        viewFlipper.adapter = adapter
        viewFlipper.isAutoStart = true
        viewFlipper.startFlipping()
    }
}