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
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six
        )
        val adapter = ViewFlipperAdapter(this@MainActivity, numberImages)
        viewFlipper.flipInterval = 42
        viewFlipper.adapter = adapter
        viewFlipper.isAutoStart = true
        viewFlipper.startFlipping()
    }
}