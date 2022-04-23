package com.example.testtasktutu.screens.details_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import com.example.testtasktutu.screens.details_screen.domain.model.RepositoriesInfoDomain
import com.example.testtasktutu.screens.details_screen.presentation.interfaces.DetailsViewModel

class DetailsViewModelImpl(private val getDataUseCase: GetDataUseCase) : ViewModel(),
    DetailsViewModel {

    private var _info = MutableLiveData<RepositoriesInfoDomain>()
    override val infoData: LiveData<RepositoriesInfoDomain> = _info

    override fun getData(login: String, name: String) {
        getDataUseCase.info.observeForever {
            _info.value = it
        }
        getDataUseCase.getData(login = login, name = name)
    }
}