package com.example.testtasktutu.list_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.list_screen.domain.RepositoryInfoDomain

interface ListViewModelInterface {
    val adapterliveData: LiveData<CustomRecyclerAdapter>
//    fun setDataManager(dataManager: DataManagerInterface)
//    fun setLambdaItemOnClick(lambdaItemClick: (RepositoryInfo) -> Unit)
    fun getAdapter(query: String, lambdaItemClick: (RepositoryInfoDomain) -> Unit)
}