package com.vitovalov.kino.ui

import android.app.Application
import com.vitovalov.kino.data.di.dataModule
import com.vitovalov.kino.domain.di.domainModule
import com.vitovalov.kino.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KinoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KinoApp)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}