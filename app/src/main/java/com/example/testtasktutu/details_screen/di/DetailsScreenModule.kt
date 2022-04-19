package com.example.testtasktutu.details_screen.di

import com.example.testtasktutu.details_screen.domain.GetDataUseCase
import com.example.testtasktutu.details_screen.viewmodel.DetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailsScreenModule {
    @Provides
    fun provideDetailsViewModelFactory(getDataUseCase: GetDataUseCase): DetailsViewModelFactory =
        DetailsViewModelFactory(getDataUseCase)
}