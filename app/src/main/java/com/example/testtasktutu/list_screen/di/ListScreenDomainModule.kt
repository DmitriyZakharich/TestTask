package com.example.testtasktutu.list_screen.di

import com.example.testtasktutu.app_data.database.AppDatabase
import com.example.testtasktutu.list_screen.domain.DataManagerImpl
import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesInfoLoader
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager
import dagger.Module
import dagger.Provides

@Module
class ListScreenDomainModule {
    @Provides
    fun provideDataManager(repositoriesInfoLoader: RepositoriesInfoLoader, appDatabase: AppDatabase): DataManager =
        DataManagerImpl(repositoriesInfoLoader, appDatabase)

    @Provides
    fun provideGetAdapterUseCase(dataManager: DataManager): GetAdapterUseCase =
        GetAdapterUseCase(dataManager)
}