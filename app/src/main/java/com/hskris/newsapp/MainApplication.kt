package com.hskris.newsapp

import android.app.Application
import com.hskris.newsapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidLogger()
            androidContext(this@MainApplication)
            androidFileProperties()
            modules(appModule)
        }

    }

}