package com.example.testtasktutu.list_screen.di

import com.example.testtasktutu.list_screen.presentation.ListFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(listFragment: ListFragment)
}