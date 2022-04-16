package com.example.testtasktutu.list_screen.domain.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain

interface RepositoriesNetworkLoader {
//    val liveData: LiveData<List<RepositoryInfoData>>?
    fun loadData(query: String, callbackList: (login: String, list: List<RepositoryInfoData>) -> Unit)
}