package com.example.testtasktutu.app_data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AppDatabase {

    private val db =
        Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoriesInfoData").fallbackToDestructiveMigration().build()
    private val repositoryInfoDao = db.getRepositoryInfoDao()

    private val _repositoriesList = MutableLiveData<List<RepositoryBriefInfoData>?>()
    val repositoriesList: LiveData<List<RepositoryBriefInfoData>?> = _repositoriesList

    private val _repositoryInfo = MutableLiveData<RepositoriesInfoData?>()
    val repositoryInfo: LiveData<RepositoriesInfoData?> = _repositoryInfo

    fun loadRepositoriesList(login: String) {
        CoroutineScope(IO).launch {
            _repositoriesList.postValue(repositoryInfoDao.getRepositoriesList(login))
        }
    }

    fun updateData(login: String, listInsert: List<RepositoriesInfoData>) {
        CoroutineScope(IO).launch {
            repositoryInfoDao.delete(login = login)
            repositoryInfoDao.insert(list = listInsert)
        }
    }

    fun loadRepositoryInfo(login: String, name: String) {
        CoroutineScope(IO).launch {
            _repositoryInfo.postValue(repositoryInfoDao.getRepositoryInfo(login = login, name = name))
        }
    }

    fun updateData(login: String, detailsInfoData: RepositoriesInfoData) {

    }
}