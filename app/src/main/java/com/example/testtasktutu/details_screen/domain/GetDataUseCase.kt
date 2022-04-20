package com.example.testtasktutu.details_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.details_screen.data.database.DetailsDatabase
import com.example.testtasktutu.details_screen.data.models.DetailsInfoData
import com.example.testtasktutu.details_screen.data.network.DetailsInfoLoaderImpl
import com.example.testtasktutu.details_screen.domain.mappers.DetailsInfoMapper
import com.example.testtasktutu.details_screen.domain.model.DetailsInfoDomain
import com.example.testtasktutu.utils.checkForInternet

class GetDataUseCase(private val detailsInfoLoader: DetailsInfoLoaderImpl,
        private val detailsDatabase: DetailsDatabase) {

    private val _info = MutableLiveData<DetailsInfoDomain>()
    val infoData: LiveData<DetailsInfoDomain> = _info

    init {
        detailsInfoLoader.parcelDetailsInfo.observeForever {
            if (it.isSuccess && it.detailsInfoData != null) {
                _info.value = DetailsInfoMapper.modelDetailsInfoToDomain(it.detailsInfoData)
                detailsDatabase.updateData(login = it.login, detailsInfoData = it.detailsInfoData)

            } else {
                detailsDatabase.loadData(login = it.login, name = it.name)
            }
        }
    }

    fun getData(login: String, name: String) {
        if (checkForInternet()) {
            detailsInfoLoader.loadData(login, name)
        } else {
            detailsDatabase.loadData(login, name)
        }
    }
}