package com.example.testtasktutu

import android.app.Application
import android.content.Context

class MyApp: Application() {


    fun getAppContext(): Context {
        return applicationContext
    }

}