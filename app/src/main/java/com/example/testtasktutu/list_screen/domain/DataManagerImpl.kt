package com.example.testtasktutu.list_screen.domain

import android.widget.Toast
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.R
import com.example.testtasktutu.app_data.database.AppDatabase
import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesInfoLoader
import com.example.testtasktutu.list_screen.domain.mappers.InfoFromBriefToFull
import com.example.testtasktutu.list_screen.domain.mappers.RepositoryInfoMapper
import com.example.testtasktutu.list_screen.domain.models.RepositoryBriefInfoDomain
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager
import com.example.testtasktutu.utils.checkForInternet

class DataManagerImpl(private val repositoriesInfoLoader: RepositoriesInfoLoader,
        private val appDatabase: AppDatabase) : DataManager {

    private var callbackListToUserCase: ((isSuccess: Boolean, List<RepositoryBriefInfoDomain>?) -> Unit)? =
        null

    init {
        appDatabase.repositoriesList.observeForever {
            if (!it.isNullOrEmpty()) {
                callbackListToUserCase?.let { lambda ->
                    lambda(true, RepositoryInfoMapper.modelListDataToDomain(it))
                }
            } else Toast.makeText(MyApp.applicationContext(),
                MyApp.applicationContext().getString(R.string.no_data_available), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun callbackListFromNetwork(isSuccess: Boolean, login: String,
            list: List<RepositoryBriefInfoData>?) {

        if (isSuccess && !list.isNullOrEmpty()) {
            list.forEach { it.login = login }

            callbackListToUserCase?.let {
                it(isSuccess, RepositoryInfoMapper.modelListDataToDomain(list))
            }
            appDatabase.updateData(login = login, listInsert = InfoFromBriefToFull.convert(list))

        } else {
            appDatabase.loadRepositoriesList(login)
        }
    }

    override fun getData(login: String,
            callbackListToUserCase: (isSuccess: Boolean, List<RepositoryBriefInfoDomain>?) -> Unit) {
        this.callbackListToUserCase = callbackListToUserCase

        if (checkForInternet()) {
            repositoriesInfoLoader.loadRepositoriesList(login, ::callbackListFromNetwork)
        } else {
            appDatabase.loadRepositoriesList(login)
        }
    }
}