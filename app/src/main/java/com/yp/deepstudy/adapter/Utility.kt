package com.yp.deepstudy.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["img"], requireAll = false)
fun imgLoad(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}