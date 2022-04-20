package com.example.testtasktutu.list_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.list_screen.domain.models.RepositoryBriefInfoDomain

interface ListViewModelInterface {
    val adapter: LiveData<CustomRecyclerAdapter>
//    fun setDataManager(dataManager: DataManagerInterface)
//    fun setLambdaItemOnClick(lambdaItemClick: (RepositoryInfo) -> Unit)
    fun getAdapter(query: String, lambdaItemClick: (RepositoryBriefInfoDomain) -> Unit)
}