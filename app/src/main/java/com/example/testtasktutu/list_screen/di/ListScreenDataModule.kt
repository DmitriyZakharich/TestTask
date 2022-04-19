package com.example.testtasktutu.list_screen.di

import com.example.testtasktutu.list_screen.data.network.RepositoriesNetworkLoaderImpl
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesNetworkLoader
import dagger.Module
import dagger.Provides

@Module
class ListScreenDataModule {
    @Provides
    fun provideRepositoriesNetworkLoader(): RepositoriesNetworkLoader =
        RepositoriesNetworkLoaderImpl()
}