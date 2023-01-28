package com.example.testtasktutu.screens.details_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.details_screen.presentation.intent.DetailsIntent
import com.example.testtasktutu.screens.details_screen.presentation.viewstate.DetailsState
import com.example.testtasktutu.screens.list_screen.presentation.intent.ListIntent
import com.example.testtasktutu.screens.list_screen.presentation.viewstate.ListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val getDataUseCase: GetDataUseCase) : ViewModel() {

    private var _info = MutableLiveData<DetailsState>()
    val info: LiveData<DetailsState> = _info

    fun handleIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.FetchDetails -> getData(intent.login, intent.name)
        }
    }

    private fun getData(login: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _info.postValue(DetailsState.Loading)
            _info.postValue(try {
                DetailsState.Details(getDataUseCase.start(login = login, name = name))
            } catch (e: Exception) {
                DetailsState.Error(e.localizedMessage)
            })
        }
    }
}