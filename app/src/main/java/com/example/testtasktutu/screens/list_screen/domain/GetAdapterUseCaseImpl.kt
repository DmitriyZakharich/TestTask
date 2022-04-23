package com.example.testtasktutu.screens.list_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testtasktutu.screens.list_screen.viewmodel.interfaces.GetAdapterUseCase
import com.example.testtasktutu.screens.list_screen.domain.models.ParcelRepositoryBriefInfo
import com.example.testtasktutu.screens.list_screen.domain.models.RepositoryBriefInfoDomain
import com.example.testtasktutu.screens.list_screen.domain.interfaces.DataManager

class GetAdapterUseCaseImpl(private val dataManager: DataManager) : GetAdapterUseCase {

    private var lambdaItemClick: ((RepositoryBriefInfoDomain) -> Unit)? = null
    private var _adapter: MutableLiveData<CustomRecyclerAdapter> = MutableLiveData()
    override var adapter: LiveData<CustomRecyclerAdapter> = _adapter

    init {
        dataManager.data.observeForever(observerNetwork())
    }

    private fun observerNetwork() = Observer<ParcelRepositoryBriefInfo> { parcel ->
        if (parcel.isSuccess && !parcel.list.isNullOrEmpty() && lambdaItemClick != null)
            _adapter.value = CustomRecyclerAdapter(data = parcel.list, clickListener = lambdaItemClick!!)
    }

    override fun start(query: String, lambdaItemClick: (RepositoryBriefInfoDomain) -> Unit) {
        this.lambdaItemClick = lambdaItemClick
        dataManager.getData(query)
    }
}
