package com.example.testtasktutu.screens.list_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testtasktutu.screens.list_screen.domain.interfaces.DataManager
import com.example.testtasktutu.screens.list_screen.domain.models.ParcelRepositoryBriefInfo
import com.example.testtasktutu.screens.list_screen.viewmodel.interfaces.GetAdapterUseCase

class GetAdapterUseCaseImpl(private val dataManager: DataManager) : GetAdapterUseCase {

    private var _adapter: MutableLiveData<CustomRecyclerAdapter> = MutableLiveData()
    override var adapter: LiveData<CustomRecyclerAdapter> = _adapter

    init {
        dataManager.data.observeForever(observerNetwork())
    }

    private fun observerNetwork() = Observer<ParcelRepositoryBriefInfo> { parcel ->
        if (parcel.isSuccess && !parcel.list.isNullOrEmpty())
            _adapter.value = CustomRecyclerAdapter(data = parcel.list)
    }

    override fun start(query: String) {
        dataManager.getData(query)
    }
}
