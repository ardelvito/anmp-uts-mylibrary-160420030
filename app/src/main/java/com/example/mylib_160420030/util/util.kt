package com.example.mylib_160420030.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?){
    Picasso.get().load(url).resize(400,400).centerCrop()

}