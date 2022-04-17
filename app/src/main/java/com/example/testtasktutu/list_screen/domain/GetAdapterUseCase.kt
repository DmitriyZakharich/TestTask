package com.example.testtasktutu.list_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataException
import com.example.testtasktutu.list_screen.domain.domain_custom_exceptions.NoAdapterException
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager

class GetAdapterUseCase(private val dataManager: DataManager) {

    private var lambdaItemClick: ((RepositoryInfoDomain) -> Unit)? = null
    private var _adapter: MutableLiveData<CustomRecyclerAdapter> = MutableLiveData()
    var adapter: LiveData<CustomRecyclerAdapter> = _adapter

    private fun callbackList(isSuccess: Boolean, list: List<RepositoryInfoDomain>?) {
        if (isSuccess && !list.isNullOrEmpty())
            _adapter.value = lambdaItemClick?.let { CustomRecyclerAdapter(list, it) }
    }

    fun start(query: String, lambdaItemClick: (RepositoryInfoDomain) -> Unit) {
        this.lambdaItemClick = lambdaItemClick

        dataManager.getData(query, ::callbackList)
    }
}
