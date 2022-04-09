package com.example.testtasktutu.list_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.list_screen.domain.RepositoryInfo

interface DataManagerInterface {
    val liveData: LiveData<List<RepositoryInfo>>?
    fun setQuery(query: String)
//    fun getData(): CustomRecyclerAdapter //    fun getData(): MutableLiveData<CustomRecyclerAdapter?>
}
