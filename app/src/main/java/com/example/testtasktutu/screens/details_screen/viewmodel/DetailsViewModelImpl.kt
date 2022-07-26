package com.example.testtasktutu.screens.details_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.details_screen.presentation.interfaces.DetailsViewModel

class DetailsViewModelImpl(private val getDataUseCase: GetDataUseCase) : ViewModel(),
    DetailsViewModel {

    private var _info = MutableLiveData<GithubDetailRepoInfoDomain>()
    override val info: LiveData<GithubDetailRepoInfoDomain> = _info

    override fun getData(login: String, name: String) {
        getDataUseCase.info.observeForever {
            _info.value = it
        }
        getDataUseCase.start(login = login, name = name)
    }
}