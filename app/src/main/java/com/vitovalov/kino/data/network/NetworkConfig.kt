package com.vitovalov.kino.data.network

import java.util.*

object NetworkConfig {
    const val API_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "***REMOVED***"
    val API_LANG: String
        get() = Locale.getDefault().language
    const val API_BASE_IMAGE = "https://image.tmdb.org/t/p/"
    const val API_BACKDROP_IMAGE_SIZE = "w1280"
}