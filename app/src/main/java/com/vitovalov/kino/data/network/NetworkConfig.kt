package com.vitovalov.kino.data.network

import java.util.*

object NetworkConfig {
    const val API_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "5d9"+"67c7c3"+"35764f"+"39b1efb"+"e9c5de9760"
    val API_LANG: String
        get() = Locale.getDefault().language
    const val API_BASE_IMAGE = "https://image.tmdb.org/t/p/"
    const val API_ORIGINAL_IMAGE = "original"
}