package com.example.testtasktutu.list_screen.data.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataInDatabaseException
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData

class AppDatabase {

    private val _liveData: MutableLiveData<List<RepositoryInfoData>> = MutableLiveData()
    val liveData: LiveData<List<RepositoryInfoData>> = _liveData

    private val db =
        Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoryInfoData").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    private val repositoryInfoDao = db.getRepositoryInfoDao()

    fun loadData(userName: String) {
        //        val db = Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
        //            "RepositoryInfoData").allowMainThreadQueries().build()
        //        val repositoryInfoDao = db.getRepositoryInfoDao()

        val localListData = repositoryInfoDao.getUser(userName)
        Log.d("TAG123321", "localListData = ${localListData.toString()}")
        if (!localListData.isNullOrEmpty()) _liveData.value = localListData
        else throw NoDataInDatabaseException()
    }

    fun updateData(userName: String, list: List<RepositoryInfoData>) {
        Log.d("TAG123321", "updateData userName = $userName")
        repositoryInfoDao.delete(userName)
        repositoryInfoDao.insert(list)

        //        repositoryInfoDao.updateUserData(userName)
    }

}