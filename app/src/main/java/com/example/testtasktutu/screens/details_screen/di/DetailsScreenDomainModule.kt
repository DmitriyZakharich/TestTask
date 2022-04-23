package com.example.testtasktutu.screens.details_screen.di

import com.example.testtasktutu.screens.details_screen.domain.GetDataUseCaseImpl
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import com.example.testtasktutu.screens.common_interfaces.AppDatabase
import com.example.testtasktutu.screens.common_interfaces.RepositoriesInfoLoader
import dagger.Module
import dagger.Provides

@Module
class DetailsScreenDomainModule {
    @Provides
    fun provideGetDataUseCase(repositoriesInfoLoader: RepositoriesInfoLoader,
            appDatabase: AppDatabase): GetDataUseCase =
        GetDataUseCaseImpl(repositoriesInfoLoader, appDatabase)
}