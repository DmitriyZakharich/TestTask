package com.example.testtasktutu.app_data.di

import com.example.testtasktutu.app_data.database.AppDatabase
import com.example.testtasktutu.app_data.network.RepositoriesInfoLoaderImpl
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesInfoLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDataModule {
    @Provides
    fun provideRepositoriesNetworkLoader(): RepositoriesInfoLoader = RepositoriesInfoLoaderImpl()

    @Provides
    fun provideAppDatabase(): AppDatabase = AppDatabase()
}