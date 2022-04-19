package com.example.testtasktutu.list_screen.di

import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase
import com.example.testtasktutu.list_screen.viewmodel.ListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ListScreenModule {
    @Provides
    fun provideListViewModelFactory(getAdapterUseCase: GetAdapterUseCase): ListViewModelFactory =
        ListViewModelFactory(getAdapterUseCase)
}