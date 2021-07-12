package com.cvitirinyu.codingchallenge.starwars.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cvitirinyu.codingchallenge.starwars.R

/**
 * Utilizes glide to load an image url into an imageView
 * Loads a placeholder image if the url is null, or in case of a network error
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.drawable.placeholder_nomoon)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)

}