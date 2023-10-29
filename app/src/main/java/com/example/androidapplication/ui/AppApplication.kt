package com.example.androidapplication.ui

import android.app.Application
import com.example.androidapplication.di.appModules
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}
