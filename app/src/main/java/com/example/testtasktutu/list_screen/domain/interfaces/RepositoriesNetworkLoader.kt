package com.example.testtasktutu.list_screen.domain.interfaces

import com.example.testtasktutu.list_screen.data.models.RepositoryBriefInfoData

interface RepositoriesNetworkLoader {
//    val liveData: LiveData<List<RepositoryInfoData>>?
    fun loadData(query: String, callbackList: (isSuccess: Boolean, login: String, list: List<RepositoryBriefInfoData>?) -> Unit)
}