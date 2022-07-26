package com.example.testtasktutu.screens.list_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.list_screen.domain.models.ParcelGithubRepoBriefInfo
import com.example.testtasktutu.screens.list_screen.viewmodel.interfaces.GetAdapterUseCase

class GetAdapterUseCaseImpl(private val repositoryManager: RepositoryManager) : GetAdapterUseCase {

    private var _adapter: MutableLiveData<CustomRecyclerAdapter> = MutableLiveData()
    override var adapter: LiveData<CustomRecyclerAdapter> = _adapter

    init {
        repositoryManager.listRepos.observeForever(observer())
    }

    private fun observer() = Observer<ParcelGithubRepoBriefInfo> { parcel ->
        if (parcel.isSuccess && !parcel.list.isNullOrEmpty())
            _adapter.value = CustomRecyclerAdapter(data = parcel.list)
    }

    override fun start(query: String) {
        repositoryManager.getListData(query)
    }
}
