package com.vitovalov.kino.data.network

import com.vitovalov.kino.data.network.dto.ShowListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {

    @GET("tv/popular")
    suspend fun getShowList(
        @Query("api_key") api: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ShowListDto

}
