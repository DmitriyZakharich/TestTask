package com.example.testtasktutu

import android.app.Application
import android.content.Context
import com.example.testtasktutu.list_screen.di.AppComponent
import com.example.testtasktutu.list_screen.di.DaggerAppComponent

class MyApp: Application() {

    lateinit var appComponent: AppComponent

    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = MyApp.applicationContext()

        appComponent = DaggerAppComponent.builder().build()
    }
}