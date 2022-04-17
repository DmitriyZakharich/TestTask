package com.example.testtasktutu.list_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain

interface DataManager {
//    val liveData: LiveData<List<RepositoryInfoDomain>>
    //    fun setQuery(query: String, callbackList: (repositories: List<RepositoryInfo?>?) -> Unit)
    fun getData(userName: String, callbackListToUserCase: (isSuccess: Boolean, list: List<RepositoryInfoDomain>?) -> Unit) //    fun getData(): MutableLiveData<CustomRecyclerAdapter?>
}
