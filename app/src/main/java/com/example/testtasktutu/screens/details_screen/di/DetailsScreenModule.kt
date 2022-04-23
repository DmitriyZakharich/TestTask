package com.example.testtasktutu.screens.details_screen.di

import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import com.example.testtasktutu.screens.details_screen.viewmodel.DetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailsScreenModule {
    @Provides
    fun provideDetailsViewModelFactory(getDataUseCase: GetDataUseCase): DetailsViewModelFactory =
        DetailsViewModelFactory(getDataUseCase)
}