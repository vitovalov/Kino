package com.vitovalov.kino.data.network

import java.util.*

object NetworkConfig {
    const val API_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "***REMOVED***"
    val API_LANG: String
        get() = Locale.getDefault().language
}