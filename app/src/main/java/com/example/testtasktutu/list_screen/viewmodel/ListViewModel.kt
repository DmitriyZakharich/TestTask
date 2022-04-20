package com.example.testtasktutu.list_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtasktutu.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase
import com.example.testtasktutu.list_screen.domain.models.RepositoryBriefInfoDomain
import com.example.testtasktutu.list_screen.presentation.interfaces.ListViewModelInterface

class ListViewModel(private val getAdapterUseCase: GetAdapterUseCase) : ViewModel(),
    ListViewModelInterface {

    private var _adapter = MutableLiveData<CustomRecyclerAdapter>()
    override val adapter: LiveData<CustomRecyclerAdapter> = _adapter

    override fun getAdapter(query: String, lambdaItemClick: (RepositoryBriefInfoDomain) -> Unit) {
        getAdapterUseCase.adapter.observeForever {
            _adapter.value = it
        }
        getAdapterUseCase.start(query, lambdaItemClick)
    }
}