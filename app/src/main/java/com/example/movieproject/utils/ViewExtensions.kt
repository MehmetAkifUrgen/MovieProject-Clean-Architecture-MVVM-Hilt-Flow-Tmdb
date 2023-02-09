package com.example.movieproject.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.movieproject.R


fun View.setGone(){
    this.visibility  = View.GONE
}

fun View.setVisible(){
    this.visibility  = View.VISIBLE
}


fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .error(R.color.purple_700)
        .into(this)
}