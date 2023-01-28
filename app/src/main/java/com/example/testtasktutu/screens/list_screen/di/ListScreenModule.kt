package com.example.testtasktutu.screens.list_screen.di

import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.interfaces.GetListUseCase
import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.ListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ListScreenModule {
    @Provides
    fun provideListViewModelFactory(getListUseCase: GetListUseCase): ListViewModelFactory =
        ListViewModelFactory(getListUseCase)
}