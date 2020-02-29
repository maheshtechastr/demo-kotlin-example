package com.mpg.demoproject

import android.app.Application

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
//        startKoin(this,
//            listOf(mainModule),
//            loadPropertiesFromFile = true)
    }
}