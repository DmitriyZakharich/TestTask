package com.example.testtasktutu.screens.details_screen.di

import com.example.testtasktutu.app_repository.RepositoryManagerImpl
import com.example.testtasktutu.app_repository.managers_interfaces.AppDatabase
import com.example.testtasktutu.app_repository.managers_interfaces.GithubReposInfoLoader
import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.details_screen.domain.GetDataUseCaseImpl
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import dagger.Module
import dagger.Provides

@Module
class DetailsScreenDomainModule {
    @Provides
    fun provideGetDataUseCase(repositoryManager: RepositoryManager): GetDataUseCase =
        GetDataUseCaseImpl(repositoryManager)

    @Provides
    fun provideRepositoryManager(githubReposInfoLoader: GithubReposInfoLoader,
            appDatabase: AppDatabase): RepositoryManager =
        RepositoryManagerImpl(githubReposInfoLoader, appDatabase)
}