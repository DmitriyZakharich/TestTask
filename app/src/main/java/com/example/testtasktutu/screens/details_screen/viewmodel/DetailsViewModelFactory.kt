package com.example.testtasktutu.screens.details_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase

class DetailsViewModelFactory(private val getDataUseCase: GetDataUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModelImpl(getDataUseCase) as T
    }
}