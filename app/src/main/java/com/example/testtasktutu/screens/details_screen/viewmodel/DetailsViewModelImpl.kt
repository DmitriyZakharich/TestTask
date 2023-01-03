package com.example.testtasktutu.screens.details_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.details_screen.presentation.interfaces.DetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModelImpl(private val getDataUseCase: GetDataUseCase) : ViewModel(),
    DetailsViewModel {

    private var _info = MutableLiveData<GithubDetailRepoInfoDomain>()
    override val info: LiveData<GithubDetailRepoInfoDomain> = _info

    override fun getData(login: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _info.postValue(getDataUseCase.start(login = login, name = name))
        }
    }
}