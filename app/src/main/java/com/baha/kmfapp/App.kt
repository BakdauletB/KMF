package com.baha.kmfapp

import android.app.Application
import com.baha.kmfapp.di.appModule
import com.baha.kmfapp.di.networkModule
import com.baha.kmfapp.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, repoModule))
        }
    }
}