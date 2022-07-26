package com.example.testtasktutu.app_repository.di

import com.example.testtasktutu.app_repository.database.AppDatabaseImpl
import com.example.testtasktutu.app_repository.managers_interfaces.AppDatabase
import com.example.testtasktutu.app_repository.managers_interfaces.GithubReposInfoLoader
import com.example.testtasktutu.app_repository.network.GithubReposInfoLoaderImpl
import dagger.Module
import dagger.Provides

@Module
class AppDataModule {
    @Provides
    fun provideGithubReposInfoLoader(): GithubReposInfoLoader = GithubReposInfoLoaderImpl()

    @Provides
    fun provideAppDatabase(): AppDatabase = AppDatabaseImpl()
}