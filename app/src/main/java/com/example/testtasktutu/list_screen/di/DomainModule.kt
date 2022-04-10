package com.example.testtasktutu.list_screen.di

import com.example.testtasktutu.list_screen.domain.DataManagerImpl
import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesNetworkLoader
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideDataManager(repositoriesNetworkLoader: RepositoriesNetworkLoader): DataManager =
        DataManagerImpl(repositoriesNetworkLoader)

    @Provides
    fun provideGetAdapterUseCase(dataManager: DataManager): GetAdapterUseCase =
        GetAdapterUseCase(dataManager)
}