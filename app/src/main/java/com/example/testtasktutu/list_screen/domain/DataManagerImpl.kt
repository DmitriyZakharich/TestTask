package com.example.testtasktutu.list_screen.domain

import android.util.Log
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NetworkExceptions
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataException
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataInDatabaseException
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

    private var callbackListToUserCase: ((List<RepositoryInfoDomain>) -> Unit)? = null
    private val appDatabase = AppDatabase()
//    private var login: String? = null

    private fun callbackListFromData(login: String, list: List<RepositoryInfoData>) {
        list.forEach { it.login = login }

        callbackListToUserCase?.let {
            it(RepositoryInfoMapper.modelListDataToDomain(list))
        }

        appDatabase.updateData(login = login, listInsert = list)
    }

    override fun getData(login: String,
            callbackListToUserCase: (List<RepositoryInfoDomain>) -> Unit) {
        this.callbackListToUserCase = callbackListToUserCase
//        this.login = login

        if (checkForInternet()) {
            Log.d("TAG123321",
                "DataManagerImpl getData first userName = $login") //            repositoriesNetworkLoader.liveData?.observeForever {
            //                appDatabase.updateData(userName, RepositoryInfoMapper.modelListDomainToData(userName, it))
            //                _liveData.value = it
            //            }
            try {
                repositoriesNetworkLoader.loadData(login,
                    ::callbackListFromData) //                appDatabase.updateData(userName = userName, list = list)
            } catch (e: IllegalStateException) {
                try {
                    appDatabase.loadData(login, ::callbackListFromData)

                } catch (e: NoDataInDatabaseException) {
                    throw NoDataException()
                }

            }
        } else try { //                Log.d("TAG123321", "нет интернета")
            //                Log.d("TAG123321", userName)
            //            appDatabase.liveData.observeForever {
            //                Log.d("TAG123321", "getData observeForever userName = $userName")
            //                _liveData.value = RepositoryInfoMapper.modelListDataToDomain(it)
            //                Log.d("TAG123321", "getData last observeForever userName = $userName")
            //
            //            }
            Log.d("TAG123321", "DataManager getData before")
            appDatabase.loadData(login, ::callbackListFromData)
            Log.d("TAG123321", "DataManager getData after")
        } catch (e: NoDataInDatabaseException) {
            throw NoDataException()
        }
    }
}