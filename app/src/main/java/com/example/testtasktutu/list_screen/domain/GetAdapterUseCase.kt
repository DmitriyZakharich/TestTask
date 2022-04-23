package com.example.testtasktutu.list_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testtasktutu.app_data.models.ParcelRepositoriesInfo
import com.example.testtasktutu.list_screen.domain.models.ParcelRepositoryBriefInfo
import com.example.testtasktutu.list_screen.domain.models.RepositoryBriefInfoDomain
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager

class GetAdapterUseCase(private val dataManager: DataManager) {

    private var lambdaItemClick: ((RepositoryBriefInfoDomain) -> Unit)? = null
    private var _adapter: MutableLiveData<CustomRecyclerAdapter> = MutableLiveData()
    var adapter: LiveData<CustomRecyclerAdapter> = _adapter

    init {
        dataManager.data.observeForever(observerNetwork())
    }

    private fun observerNetwork() = Observer<ParcelRepositoryBriefInfo> { parcel ->
        if (parcel.isSuccess && !parcel.list.isNullOrEmpty() && lambdaItemClick != null)
            _adapter.value = CustomRecyclerAdapter(data = parcel.list, clickListener = lambdaItemClick!!)
    }

    fun start(query: String, lambdaItemClick: (RepositoryBriefInfoDomain) -> Unit) {
        this.lambdaItemClick = lambdaItemClick
        dataManager.getData(query)
    }
}
