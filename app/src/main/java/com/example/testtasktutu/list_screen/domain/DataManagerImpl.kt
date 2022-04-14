package com.example.testtasktutu.list_screen.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _liveData: MutableLiveData<List<RepositoryInfoDomain>> =
        MutableLiveData<List<RepositoryInfoDomain>>()
    override val liveData: LiveData<List<RepositoryInfoDomain>> = _liveData
    private var callbackListToUserCase: ((List<RepositoryInfoDomain>) -> Unit)? = null
    private val appDatabase = AppDatabase()

    private fun callbackListFromData(list: List<RepositoryInfoData>){
        callbackListToUserCase?.let { it(RepositoryInfoMapper.modelListDataToDomain(list)) }
//        _liveData.value = RepositoryInfoMapper.modelListDataToDomain(list)
    }

    override fun getData(userName: String, callbackListToUserCase: (List<RepositoryInfoDomain>) -> Unit) {
        this.callbackListToUserCase = callbackListToUserCase

        if (checkForInternet()) {
            Log.d("TAG123321", "DataManagerImpl getData first userName = $userName")

//            repositoriesNetworkLoader.liveData?.observeForever {
//                appDatabase.updateData(userName, RepositoryInfoMapper.modelListDomainToData(userName, it))
//                _liveData.value = it
//            }
            try {
                repositoriesNetworkLoader.loadData(userName, ::callbackListFromData)
            } catch (e: NetworkExceptions) {
                try {

                    //                    Log.d("TAG123321", "не загрузилось ")
//                    appDatabase.liveData.observeForever {
//                        _liveData.value = RepositoryInfoMapper.modelListDataToDomain(it)
//
//                    }

                    appDatabase.loadData(userName, ::callbackListFromData)

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
            appDatabase.loadData(userName, ::callbackListFromData)
            Log.d("TAG123321", "DataManager getData after")
        } catch (e: NoDataInDatabaseException) {
            throw NoDataException()
        }
    }
}