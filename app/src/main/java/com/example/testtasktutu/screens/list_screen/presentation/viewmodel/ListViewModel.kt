package com.example.testtasktutu.screens.list_screen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasktutu.screens.list_screen.presentation.intent.ListIntent
import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.interfaces.GetListUseCase
import com.example.testtasktutu.screens.list_screen.presentation.viewstate.ListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val getListUseCase: GetListUseCase) : ViewModel() {

    private var _repos = MutableLiveData<ListState>()
    val repos: LiveData<ListState> = _repos

    fun handleIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.FetchUser -> getList(intent.query)
        }
    }

    private fun getList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _repos.postValue(ListState.Loading)
            _repos.postValue(try {
                val list = getListUseCase.start(query)
                if (list.isNotEmpty()) {
                    ListState.Repos(list)
                } else {
                    ListState.Idle
                }
            } catch (e: Exception) {
                ListState.Error(e.localizedMessage)
            })
        }
    }
}