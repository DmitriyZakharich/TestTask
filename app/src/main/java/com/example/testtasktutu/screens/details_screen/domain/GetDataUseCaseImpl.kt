package com.example.testtasktutu.screens.details_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.screens.list_screen.domain.models.ParcelGithubRepoBriefInfo

class GetDataUseCaseImpl(private val repositoryManager: RepositoryManager) : GetDataUseCase {

    private val _info = MutableLiveData<GithubDetailRepoInfoDomain>()
    override val info: LiveData<GithubDetailRepoInfoDomain> = _info

    init {
        repositoryManager.detailRepo.observeForever(observerNetwork())
    }


    private fun observerNetwork() = Observer<GithubDetailRepoInfoDomain> { detailRepo ->
//        if (parcel.isSuccess && !parcel.list.isNullOrEmpty())
            _info.value = detailRepo
    }



    override fun start(login: String, name: String) {
        repositoryManager.getDetailsData(login = login, name = name)
    }
}