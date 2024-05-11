package com.example.testtasktutu.presentation.details_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetDetailsUseCase
import com.example.testtasktutu.presentation.details_screen.intent.DetailsIntent
import com.example.testtasktutu.presentation.details_screen.viewstate.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getDetailsUseCase: GetDetailsUseCase) : ViewModel() {

    private var _info = MutableLiveData<DetailsState>()
    val info: LiveData<DetailsState> = _info

    fun handleIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.FetchDetails -> getData(intent.login)
        }
    }

    private fun getData(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _info.postValue(DetailsState.Loading)
            _info.postValue(try {
                DetailsState.Details(getDetailsUseCase.execute(login = login))
            } catch (e: Exception) {
                DetailsState.Error(e.localizedMessage)
            })
        }
    }
}