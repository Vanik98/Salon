package com.example

import android.app.Application
import com.example.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class SalonApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() = org.koin.core.context.startKoin {
        androidLogger(Level.DEBUG)
        androidContext(this@SalonApp)
        modules(appModules)
    }
}