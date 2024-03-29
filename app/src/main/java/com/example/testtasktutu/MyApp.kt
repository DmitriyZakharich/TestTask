package com.example.testtasktutu

import android.app.Application
import android.content.Context
import com.example.testtasktutu.screens.details_screen.di.DaggerDetailsScreenComponent
import com.example.testtasktutu.screens.details_screen.di.DetailsScreenComponent
import com.example.testtasktutu.screens.list_screen.di.ListScreenComponent
import com.example.testtasktutu.screens.list_screen.di.DaggerListScreenComponent

class MyApp: Application() {

    lateinit var listScreenComponent: ListScreenComponent
    lateinit var detailsScreenComponent: DetailsScreenComponent

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
        listScreenComponent = DaggerListScreenComponent.builder().build()
        detailsScreenComponent = DaggerDetailsScreenComponent.builder().build()
    }
}