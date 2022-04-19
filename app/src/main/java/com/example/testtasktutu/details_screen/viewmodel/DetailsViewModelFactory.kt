package com.example.testtasktutu.details_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.details_screen.domain.GetDataUseCase
import com.example.testtasktutu.details_screen.viewmodel.DetailsViewModel
import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase

class DetailsViewModelFactory(private val getDataUseCase: GetDataUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(getDataUseCase) as T
    }
}