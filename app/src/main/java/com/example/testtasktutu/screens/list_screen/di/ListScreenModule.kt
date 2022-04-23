package com.example.testtasktutu.screens.list_screen.di

import com.example.testtasktutu.screens.list_screen.viewmodel.interfaces.GetAdapterUseCase
import com.example.testtasktutu.screens.list_screen.viewmodel.ListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ListScreenModule {
    @Provides
    fun provideListViewModelFactory(getAdapterUseCase: GetAdapterUseCase): ListViewModelFactory =
        ListViewModelFactory(getAdapterUseCase)
}