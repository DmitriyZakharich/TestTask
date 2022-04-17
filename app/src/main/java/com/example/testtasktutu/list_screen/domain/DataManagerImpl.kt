package com.example.testtasktutu.list_screen.domain

import android.util.Log
import android.widget.Toast
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.R
import com.example.testtasktutu.list_screen.data.database.AppDatabase
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesNetworkLoader
import com.example.testtasktutu.list_screen.domain.mappers.RepositoryInfoMapper
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager
import com.example.testtasktutu.utils.checkForInternet

/**Получает запрос на загрузку данных из GetAdapterUseCase
 * Пытается загрузить данные из сети и обновляет данные в кеше
 * Если не получается, то загружает данные из кеша*/

class DataManagerImpl(private val repositoriesNetworkLoader: RepositoriesNetworkLoader) :
    DataManager {

    private var callbackListToUserCase: ((isSuccess: Boolean, List<RepositoryInfoDomain>?) -> Unit)? =
        null
    private val appDatabase = AppDatabase()

    init {
        appDatabase.livedata.observeForever{
            if (!it.isNullOrEmpty()) {
                callbackListToUserCase?.let {lambda ->
                    lambda(true, RepositoryInfoMapper.modelListDataToDomain(it))
                }
            } else Toast.makeText(MyApp.applicationContext(),
                MyApp.applicationContext().getString(R.string.no_data_available), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun callbackListFromNetwork(isSuccess: Boolean, login: String,
            list: List<RepositoryInfoData>?) {

        if (isSuccess && !list.isNullOrEmpty()) {
            list.forEach { it.login = login }

            callbackListToUserCase?.let {
                it(isSuccess, RepositoryInfoMapper.modelListDataToDomain(list))
            }
            appDatabase.updateData(login = login, listInsert = list)

        } else {
            appDatabase.loadData(login)
        }
    }

    override fun getData(userName: String,
            callbackListToUserCase: (isSuccess: Boolean, List<RepositoryInfoDomain>?) -> Unit) {
        this.callbackListToUserCase = callbackListToUserCase

        if (checkForInternet()) {
            repositoriesNetworkLoader.loadData(userName, ::callbackListFromNetwork)
        } else {
            appDatabase.loadData(userName)
        }
    }
}