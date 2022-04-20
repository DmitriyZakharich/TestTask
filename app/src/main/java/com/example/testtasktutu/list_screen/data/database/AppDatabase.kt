package com.example.testtasktutu.list_screen.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.details_screen.data.models.DetailsInfoData
import com.example.testtasktutu.list_screen.data.models.RepositoryBriefInfoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AppDatabase {

    private val db =
        Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoryInfoData").fallbackToDestructiveMigration().build()
    private val repositoryInfoDao = db.getRepositoryInfoDao()

    private val _livedata = MutableLiveData<DetailsInfoData?>()
    val livedata: LiveData<DetailsInfoData?> = _livedata

    fun loadData(login: String) {
        CoroutineScope(IO).launch {
//            _livedata.postValue(repositoryInfoDao.getUser(login))
        }
    }

    fun updateData(login: String, listInsert: List<RepositoryBriefInfoData>) {
        CoroutineScope(IO).launch {
            repositoryInfoDao.delete(login)
            repositoryInfoDao.insert(listInsert)
        }
    }
}