package com.xiaozhezhe.widgettest

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop


class ViewFlipperAdapter : BaseAdapter {
    private var mContext: Context? = null
    private var imageList: IntArray? = null
    var inflter: LayoutInflater? = null

    constructor(
        applicationContext: Context,
        colorImages: IntArray
    ) {
        mContext = applicationContext
        imageList = colorImages
        inflter = LayoutInflater.from(applicationContext)
    }

    override fun getCount(): Int {
        return imageList?.size ?: 0
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = inflter?.inflate(R.layout.item_widget_image, null)
        val imageView = view?.findViewById<ImageView>(R.id.widgetImage)
        mContext?.let {
            Glide.with(it)
                .load(imageList?.get(position))
                .transform(CenterCrop(), RoundedCorners(40))
                .into(imageView!!)
        }
        return view
    }
}