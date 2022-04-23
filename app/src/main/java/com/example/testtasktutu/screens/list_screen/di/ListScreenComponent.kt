package com.example.testtasktutu.screens.list_screen.di

import com.example.testtasktutu.app_data.di.AppDataModule
import com.example.testtasktutu.screens.list_screen.presentation.ListFragment
import dagger.Component

@Component(modules = [ListScreenModule::class, ListScreenDomainModule::class, AppDataModule::class])
interface ListScreenComponent {
    fun inject(listFragment: ListFragment)
}