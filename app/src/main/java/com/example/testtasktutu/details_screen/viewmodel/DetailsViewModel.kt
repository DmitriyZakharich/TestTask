package com.example.testtasktutu.details_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtasktutu.details_screen.domain.GetDataUseCase
import com.example.testtasktutu.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain
import com.example.testtasktutu.list_screen.presentation.interfaces.ListViewModelInterface

class DetailsViewModel(private val getDataUseCase: GetDataUseCase) : ViewModel(){

    private var _adapter = MutableLiveData<CustomRecyclerAdapter>()
    val adapter: LiveData<CustomRecyclerAdapter> = _adapter

    fun getData(query: String) {
        
    }
}