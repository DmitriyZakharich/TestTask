package com.example.testtasktutu.screens.list_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasktutu.screens.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.screens.list_screen.viewmodel.interfaces.GetAdapterUseCase
import com.example.testtasktutu.screens.list_screen.presentation.interfaces.ListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModelImpl(private val getAdapterUseCase: GetAdapterUseCase) : ViewModel(),
    ListViewModel {

    private var _adapter = MutableLiveData<CustomRecyclerAdapter>()
    override val adapter: LiveData<CustomRecyclerAdapter> = _adapter

    override fun getAdapter(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _adapter.postValue(getAdapterUseCase.start(query))
        }
    }
}