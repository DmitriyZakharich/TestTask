package com.example.testtasktutu.details_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtasktutu.details_screen.domain.GetDataUseCase
import com.example.testtasktutu.details_screen.domain.model.RepositoriesInfoDomain

class DetailsViewModel(private val getDataUseCase: GetDataUseCase) : ViewModel() {

    private var _info = MutableLiveData<RepositoriesInfoDomain>()
    val infoData: LiveData<RepositoriesInfoDomain> = _info

    fun getData(login: String, name: String) {
        getDataUseCase.info.observeForever {
            _info.value = it
        }
        getDataUseCase.getData(login = login, name = name)
    }
}