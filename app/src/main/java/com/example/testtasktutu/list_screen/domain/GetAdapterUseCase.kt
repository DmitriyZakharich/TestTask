package com.example.testtasktutu.list_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.list_screen.domain.models.RepositoryBriefInfoDomain
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager

class GetAdapterUseCase(private val dataManager: DataManager) {

    private var lambdaItemClick: ((RepositoryBriefInfoDomain) -> Unit)? = null
    private var _adapter: MutableLiveData<CustomRecyclerAdapter> = MutableLiveData()
    var adapter: LiveData<CustomRecyclerAdapter> = _adapter

    private fun callbackList(isSuccess: Boolean, list: List<RepositoryBriefInfoDomain>?) {
        if (isSuccess && !list.isNullOrEmpty()) _adapter.value =
            lambdaItemClick?.let { CustomRecyclerAdapter(list, it) }
    }

    fun start(query: String, lambdaItemClick: (RepositoryBriefInfoDomain) -> Unit) {
        this.lambdaItemClick = lambdaItemClick

        dataManager.getData(query, ::callbackList)
    }
}
