package com.example.testtasktutu

import android.app.Application
import android.content.Context
import com.example.testtasktutu.list_screen.di.ListScreenComponent
import com.example.testtasktutu.list_screen.di.DaggerListScreenComponent

class MyApp: Application() {

    lateinit var listScreenComponent: ListScreenComponent

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
        val context: Context = applicationContext()

        listScreenComponent = DaggerListScreenComponent.builder().build()
    }
}