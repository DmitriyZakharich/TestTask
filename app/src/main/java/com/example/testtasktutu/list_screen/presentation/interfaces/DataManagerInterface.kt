package com.example.testtasktutu.list_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain

interface DataManagerInterface {
    val liveData: LiveData<List<RepositoryInfoDomain>>
    //    fun setQuery(query: String, callbackList: (repositories: List<RepositoryInfo?>?) -> Unit)
    fun getData(userName: String) //    fun getData(): MutableLiveData<CustomRecyclerAdapter?>
}
