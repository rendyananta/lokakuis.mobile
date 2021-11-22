package com.example.lokakuis.base.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(url: String?) {
    if (url != "" && url != null) {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("loadImageUrlCircled")
fun ImageView.loadImageUrlCircled(url: String?) {
    if (url != "" && url != null) {
        Glide.with(context)
            .load(url)
            .circleCrop()
            .into(this)
    }
}
