package com.example.testtasktutu.list_screen.domain.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.list_screen.domain.RepositoryInfo

interface RetrofitRepositoriesLoaderInterface {
    val liveData: LiveData<List<RepositoryInfo>>?
    fun loadData(query: String)
}