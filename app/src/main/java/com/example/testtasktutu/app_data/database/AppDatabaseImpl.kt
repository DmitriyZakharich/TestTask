package com.example.testtasktutu.app_data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData
import com.example.testtasktutu.screens.common_interfaces.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AppDatabaseImpl : AppDatabase {

    private val db =
        Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoriesInfoData").fallbackToDestructiveMigration().build()
    private val repositoryInfoDao = db.getRepositoryInfoDao()

    private val _repositoriesList = MutableLiveData<List<RepositoryBriefInfoData>?>()
    override val repositoriesList: LiveData<List<RepositoryBriefInfoData>?> = _repositoriesList

    private val _repositoryInfo = MutableLiveData<RepositoriesInfoData?>()
    override val repositoryInfo: LiveData<RepositoriesInfoData?> = _repositoryInfo

    override fun loadRepositoriesList(login: String) {
        CoroutineScope(IO).launch {
            _repositoriesList.postValue(repositoryInfoDao.getRepositoriesList(login))
        }
    }

    override fun updateData(login: String, listInsert: List<RepositoriesInfoData>) {
        CoroutineScope(IO).launch {
            repositoryInfoDao.delete(login = login)
            repositoryInfoDao.insert(list = listInsert)
        }
    }

    override fun loadRepositoryInfo(login: String, name: String) {
        CoroutineScope(IO).launch {
            _repositoryInfo.postValue(
                repositoryInfoDao.getRepositoryInfo(login = login, name = name))
        }
    }

    override fun updateData(detailsInfoData: RepositoriesInfoData) {
        val id = MutableLiveData<Int>()
        id.observeForever {
            detailsInfoData.id = it
            CoroutineScope(IO).launch {
                repositoryInfoDao.updateRepositoryInfo(detailsInfoData)
            }
        }

        if (!detailsInfoData.login.isNullOrEmpty() && !detailsInfoData.name.isNullOrEmpty()) {
            CoroutineScope(IO).launch {
                id.postValue(repositoryInfoDao.getRepositoryInfo(login = detailsInfoData.login!!,
                    name = detailsInfoData.name!!).id)
            }
        }
    }
}