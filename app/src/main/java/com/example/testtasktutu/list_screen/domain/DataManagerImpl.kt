package com.example.testtasktutu.list_screen.domain

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.R
import com.example.testtasktutu.app_data.database.AppDatabase
import com.example.testtasktutu.app_data.models.ParcelRepositoriesInfo
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesInfoLoader
import com.example.testtasktutu.list_screen.domain.mappers.InfoFromBriefToFull
import com.example.testtasktutu.list_screen.domain.mappers.RepositoryInfoMapper
import com.example.testtasktutu.list_screen.domain.models.ParcelRepositoryBriefInfo
import com.example.testtasktutu.list_screen.domain.models.RepositoryBriefInfoDomain
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager
import com.example.testtasktutu.utils.checkForInternet

class DataManagerImpl(private val repositoriesInfoLoader: RepositoriesInfoLoader,
        private val appDatabase: AppDatabase) : DataManager {

    private val _data = MutableLiveData<ParcelRepositoryBriefInfo>()
    override val data: LiveData<ParcelRepositoryBriefInfo> = _data

    init {
        repositoriesInfoLoader.parcelRepositoryInfo.observeForever(observerNetwork())
        appDatabase.repositoriesList.observeForever(observerDatabase())
    }

    private fun observerNetwork() = Observer<ParcelRepositoriesInfo> { parcel ->
        if (parcel.isSuccess && !parcel.listRepositoriesInfo.isNullOrEmpty()) {
            parcel.listRepositoriesInfo.forEach { list ->
                list?.login = parcel.login
            }

            _data.value = ParcelRepositoryBriefInfo(parcel.isSuccess, RepositoryInfoMapper.modelListDataToDomain(
                parcel.listRepositoriesInfo as List<RepositoryBriefInfoData>))

            appDatabase.updateData(login = parcel.login, listInsert = InfoFromBriefToFull.convert(
                parcel.listRepositoriesInfo))

        } else {
            appDatabase.loadRepositoriesList(parcel.login)
        }
    }

    private fun observerDatabase() = Observer<List<RepositoryBriefInfoData>?> { repositoriesList ->
        if (!repositoriesList.isNullOrEmpty()) {
            _data.value = ParcelRepositoryBriefInfo(true, RepositoryInfoMapper.modelListDataToDomain(repositoriesList))

        } else Toast.makeText(MyApp.applicationContext(),
            MyApp.applicationContext().getString(R.string.no_data_available), Toast.LENGTH_LONG)
            .show()
    }

    override fun getData(login: String) {
        if (checkForInternet()) {
            repositoriesInfoLoader.loadRepositoriesList(login)
        } else {
            appDatabase.loadRepositoriesList(login)
        }
    }
}