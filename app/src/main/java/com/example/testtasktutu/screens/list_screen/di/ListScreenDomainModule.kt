package com.example.testtasktutu.screens.list_screen.di

import com.example.testtasktutu.screens.list_screen.domain.DataManagerImpl
import com.example.testtasktutu.screens.list_screen.domain.GetAdapterUseCaseImpl
import com.example.testtasktutu.screens.common_interfaces.AppDatabase
import com.example.testtasktutu.screens.list_screen.viewmodel.interfaces.GetAdapterUseCase
import com.example.testtasktutu.screens.common_interfaces.RepositoriesInfoLoader
import com.example.testtasktutu.screens.list_screen.domain.interfaces.DataManager
import dagger.Module
import dagger.Provides

@Module
class ListScreenDomainModule {
    @Provides
    fun provideDataManager(repositoriesInfoLoader: RepositoriesInfoLoader, appDatabase: AppDatabase): DataManager =
        DataManagerImpl(repositoriesInfoLoader, appDatabase)

    @Provides
    fun provideGetAdapterUseCase(dataManager: DataManager): GetAdapterUseCase =
        GetAdapterUseCaseImpl(dataManager)
}