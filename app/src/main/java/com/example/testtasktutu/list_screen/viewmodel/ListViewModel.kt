package com.example.testtasktutu.list_screen.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain
import com.example.testtasktutu.list_screen.domain.domain_custom_exceptions.NoAdapterException
import com.example.testtasktutu.list_screen.presentation.interfaces.ListViewModelInterface

/**Запрос данных из DataManager*/
class ListViewModel(private val getAdapterUseCase: GetAdapterUseCase) : ViewModel(), ListViewModelInterface {

    private var _adapter = MutableLiveData<CustomRecyclerAdapter>()
    override val adapterliveData: LiveData<CustomRecyclerAdapter> = _adapter

    override fun getAdapter(query: String, lambdaItemClick: (RepositoryInfoDomain) -> Unit) {
        getAdapterUseCase.adapter.observeForever{
            _adapter.value = it
        }
        try {
            getAdapterUseCase.start(query, lambdaItemClick)
        }catch (e: NoAdapterException){
            Toast.makeText(MyApp.applicationContext(), "Ошибка при получении данных", Toast.LENGTH_LONG).show()
        }
    }
}