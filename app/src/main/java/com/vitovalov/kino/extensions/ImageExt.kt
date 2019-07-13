package com.vitovalov.kino.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.load(url: String) = Glide.with(this).load(url).into(this)
