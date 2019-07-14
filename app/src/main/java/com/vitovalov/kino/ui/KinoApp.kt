package com.vitovalov.kino.ui

import android.app.Application
import com.vitovalov.kino.BuildConfig
import com.vitovalov.kino.data.di.dataModule
import com.vitovalov.kino.domain.di.domainModule
import com.vitovalov.kino.ui.di.appModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class KinoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KinoApp)
            modules(listOf(appModule, domainModule, dataModule))
        }

        setupLogger()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
