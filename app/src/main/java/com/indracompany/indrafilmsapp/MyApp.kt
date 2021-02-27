package com.indracompany.indrafilmsapp

import android.app.Application
import com.indracompany.indrafilmsapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}