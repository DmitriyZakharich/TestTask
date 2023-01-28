package com.example.testtasktutu.screens.list_screen.di

import com.example.testtasktutu.app_repository.RepositoryManagerImpl
import com.example.testtasktutu.app_repository.managers_interfaces.AppDatabase
import com.example.testtasktutu.app_repository.managers_interfaces.GithubReposInfoLoader
import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.list_screen.domain.GetListUseCaseImpl
import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.interfaces.GetListUseCase
import dagger.Module
import dagger.Provides

@Module
class ListScreenDomainModule {
    @Provides
    fun provideRepositoryManager(githubReposInfoLoader: GithubReposInfoLoader,
            appDatabase: AppDatabase): RepositoryManager =
        RepositoryManagerImpl(githubReposInfoLoader, appDatabase)

    @Provides
    fun provideGetAdapterUseCase(repositoryManager: RepositoryManager): GetListUseCase =
        GetListUseCaseImpl(repositoryManager)
}