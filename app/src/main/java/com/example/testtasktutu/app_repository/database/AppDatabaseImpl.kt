package com.example.testtasktutu.app_repository.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.app_repository.managers_interfaces.AppDatabase
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AppDatabaseImpl : AppDatabase {

    private val db =
        Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoriesInfoData").fallbackToDestructiveMigration().build()
    private val repositoryInfoDao = db.getRepositoryInfoDao()

    private val _repositoriesList = MutableLiveData<List<GithubRepoBriefInfoData>?>()
    override val repositoriesList: LiveData<List<GithubRepoBriefInfoData>?> = _repositoriesList

    private val _repositoryInfo = MutableLiveData<GithubRepoInfoData?>()
    override val repositoryInfo: LiveData<GithubRepoInfoData?> = _repositoryInfo

    override fun loadGithubRepositoriesList(login: String) {
        CoroutineScope(IO).launch {
            _repositoriesList.postValue(repositoryInfoDao.getGithubReposList(login))
        }
    }

    override fun updateData(login: String, listInsert: List<GithubRepoInfoData>) {
        CoroutineScope(IO).launch {
            repositoryInfoDao.delete(login = login)
            repositoryInfoDao.insert(list = listInsert)
        }
    }

    override fun loadRepositoryInfo(login: String, name: String) {
        CoroutineScope(IO).launch {
            _repositoryInfo.postValue(
                repositoryInfoDao.getGithubRepoInfo(login = login, name = name))
        }
    }

    override fun updateData(detailsInfoData: GithubRepoInfoData) {
        val id = MutableLiveData<Int>()
        id.observeForever {
            detailsInfoData.id = it
            CoroutineScope(IO).launch {
                repositoryInfoDao.updateRepositoryInfo(detailsInfoData)
            }
        }

        if (!detailsInfoData.login.isNullOrEmpty() && !detailsInfoData.name.isNullOrEmpty()) {
            CoroutineScope(IO).launch {
                id.postValue(repositoryInfoDao.getGithubRepoInfo(login = detailsInfoData.login!!,
                    name = detailsInfoData.name!!).id)
            }
        }
    }
}