package com.vitovalov.kino.data.di

import com.vitovalov.kino.data.ShowListDataSource
import com.vitovalov.kino.data.local.ShowListLocalDataSource
import com.vitovalov.kino.data.local.ShowListRealmDataSource
import com.vitovalov.kino.data.local.mapper.ShowLoMapper
import com.vitovalov.kino.data.mapper.ShowListDtoMapper
import com.vitovalov.kino.data.network.MovieDbApi
import com.vitovalov.kino.data.network.NetworkConfig
import com.vitovalov.kino.data.network.ShowListNetworkDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val dataModule = module {
    single<ShowListDataSource> { ShowListNetworkDataSource(get(), get()) }
    single { ShowListDtoMapper() }
    single<ShowListLocalDataSource> { ShowListRealmDataSource(get()) }
    single { ShowLoMapper() }
    single<MovieDbApi> { get<Retrofit>().create() }

    single {
        Retrofit.Builder()
            .apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(NetworkConfig.API_URL)
                client(get())
            }
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    factory {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return@factory interceptor
    }
}
