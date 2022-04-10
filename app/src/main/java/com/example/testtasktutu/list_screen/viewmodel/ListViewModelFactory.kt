package com.example.testtasktutu.list_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase

class ListViewModelFactory(private val getAdapterUseCase: GetAdapterUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(getAdapterUseCase) as T
    }
}