package com.example.testtasktutu.screens.details_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testtasktutu.app_data.models.ParcelDetailsInfo
import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.screens.details_screen.domain.interfaces.GetDataUseCase
import com.example.testtasktutu.screens.details_screen.domain.mappers.RepositoriesInfoMapper
import com.example.testtasktutu.screens.details_screen.domain.model.RepositoriesInfoDomain
import com.example.testtasktutu.screens.common_interfaces.AppDatabase
import com.example.testtasktutu.screens.common_interfaces.RepositoriesInfoLoader
import com.example.testtasktutu.utils.checkForInternet

class GetDataUseCaseImpl(private val repositoriesInfoLoader: RepositoriesInfoLoader,
        private val appDatabase: AppDatabase) : GetDataUseCase {

    private val _info = MutableLiveData<RepositoriesInfoDomain>()
    override val info: LiveData<RepositoriesInfoDomain> = _info

    init {
        repositoriesInfoLoader.parcelDetailsInfo.observeForever(observer())
        appDatabase.repositoryInfo.observeForever(observerDB())
    }

    private fun observer() = Observer<ParcelDetailsInfo> {
        if (it.isSuccess && it.detailsInfoData != null) {
            _info.value = RepositoriesInfoMapper.modelRepositoriesInfoToDomain(it.detailsInfoData)

            it.detailsInfoData.login = it.login
            appDatabase.updateData(detailsInfoData = it.detailsInfoData)

        } else {
            appDatabase.loadRepositoryInfo(login = it.login, name = it.name)
        }
    }

    private fun observerDB() = Observer<RepositoriesInfoData?> {
        _info.value = RepositoriesInfoMapper.modelRepositoriesInfoToDomain(it)
    }

    override fun getData(login: String, name: String) {
        if (checkForInternet()) {
            repositoriesInfoLoader.loadRepositoryInfo(login = login, name = name)
        } else {
            appDatabase.loadRepositoryInfo(login = login, name = name)
        }
    }
}