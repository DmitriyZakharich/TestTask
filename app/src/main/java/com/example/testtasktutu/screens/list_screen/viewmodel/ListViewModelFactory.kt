package com.example.testtasktutu.screens.list_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.screens.list_screen.viewmodel.interfaces.GetAdapterUseCase

class ListViewModelFactory(private val getAdapterUseCase: GetAdapterUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModelImpl(getAdapterUseCase) as T
    }
}