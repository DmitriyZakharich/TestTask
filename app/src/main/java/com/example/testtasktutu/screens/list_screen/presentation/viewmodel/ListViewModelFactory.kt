package com.example.testtasktutu.screens.list_screen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.interfaces.GetListUseCase

class ListViewModelFactory(private val getListUseCase: GetListUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(getListUseCase) as T
    }
}