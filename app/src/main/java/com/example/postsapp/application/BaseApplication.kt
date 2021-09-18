package com.example.postsapp.application

import android.app.Application
import com.example.postsapp.application.di.*
import com.example.postsapp.BuildConfig
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(Level.NONE)
            modules(
                listOf(
                    retrofitModule,
                    roomModule,
                    dataSourceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}
