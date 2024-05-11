package com.example.testtasktutu.di

import com.example.domain.repository.RepositoryManager
import com.example.domain.usecases.GetDetailsUseCase
import com.example.domain.usecases.GetListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetListUseCase(repositoryManager: RepositoryManager): GetListUseCase =
        GetListUseCase(repositoryManager)

    @Provides
    fun provideGetDataUseCase(repositoryManager: RepositoryManager): GetDetailsUseCase =
        GetDetailsUseCase(repositoryManager)
}