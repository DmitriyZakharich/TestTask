package com.example.testtasktutu.details_screen.di

import com.example.testtasktutu.app_data.database.AppDatabase
import com.example.testtasktutu.details_screen.domain.GetDataUseCase
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesInfoLoader
import dagger.Module
import dagger.Provides

@Module
class DetailsScreenDomainModule {
    @Provides
    fun provideGetDataUseCase(repositoriesInfoLoader: RepositoriesInfoLoader, appDatabase: AppDatabase): GetDataUseCase =
        GetDataUseCase(repositoriesInfoLoader, appDatabase)
}