package com.example.testtasktutu.list_screen.data.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AppDatabase {

    //В MyApp или даггер?
    //Добавить ассинхронность
    private val db =
        Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoryInfoData").fallbackToDestructiveMigration().build()
    private val repositoryInfoDao = db.getRepositoryInfoDao()
    private val _livedata = MutableLiveData<List<RepositoryInfoData>?>()
    val livedata: LiveData<List<RepositoryInfoData>?> = _livedata

    fun loadData(login: String) {

        CoroutineScope(IO).launch {
            _livedata.postValue(repositoryInfoDao.getUser(login))
            Log.d("TAG545454", "CoroutineScope: ")
        }
    }

    fun updateData(login: String, listInsert: List<RepositoryInfoData>) {
        CoroutineScope(IO).launch {
            repositoryInfoDao.delete(login)
            repositoryInfoDao.insert(listInsert)
        }
    }
}