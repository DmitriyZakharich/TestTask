package com.example.testtasktutu.app_data.di

import com.example.testtasktutu.app_data.database.AppDatabaseImpl
import com.example.testtasktutu.app_data.network.RepositoriesInfoLoaderImpl
import com.example.testtasktutu.screens.common_interfaces.AppDatabase
import com.example.testtasktutu.screens.common_interfaces.RepositoriesInfoLoader
import dagger.Module
import dagger.Provides

@Module
class AppDataModule {
    @Provides
    fun provideRepositoriesNetworkLoader(): RepositoriesInfoLoader = RepositoriesInfoLoaderImpl()

    @Provides
    fun provideAppDatabase(): AppDatabase = AppDatabaseImpl()
}