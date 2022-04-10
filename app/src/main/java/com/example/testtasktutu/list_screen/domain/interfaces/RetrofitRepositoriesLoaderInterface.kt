package com.example.testtasktutu.list_screen.domain.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain

interface RetrofitRepositoriesLoaderInterface {
    val liveData: LiveData<List<RepositoryInfoDomain>>?
    fun loadData(query: String)
}