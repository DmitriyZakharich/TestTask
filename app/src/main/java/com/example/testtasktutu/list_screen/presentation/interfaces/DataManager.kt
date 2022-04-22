package com.example.testtasktutu.list_screen.presentation.interfaces

import com.example.testtasktutu.list_screen.domain.models.RepositoryBriefInfoDomain

interface DataManager {
//    val liveData: LiveData<List<RepositoryInfoDomain>>
    //    fun setQuery(query: String, callbackList: (repositories: List<RepositoryInfo?>?) -> Unit)
    fun getData(login: String, callbackListToUserCase: (isSuccess: Boolean, list: List<RepositoryBriefInfoDomain>?) -> Unit) //    fun getData(): MutableLiveData<CustomRecyclerAdapter?>
}
