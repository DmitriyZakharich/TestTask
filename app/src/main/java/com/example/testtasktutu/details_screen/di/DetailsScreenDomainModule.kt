package com.example.testtasktutu.details_screen.di

import com.example.testtasktutu.details_screen.domain.GetDataUseCase
import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager
import dagger.Module
import dagger.Provides

@Module
class DetailsScreenDomainModule {
    @Provides
    fun provideGetDataUseCase(): GetDataUseCase =
        GetDataUseCase()
}