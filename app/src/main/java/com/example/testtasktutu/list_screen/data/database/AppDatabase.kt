package com.example.testtasktutu.list_screen.data.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataInDatabaseException
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData

class AppDatabase {

//    private val _liveData: MutableLiveData<List<RepositoryInfoData>> = MutableLiveData()
//    val liveData: LiveData<List<RepositoryInfoData>> = _liveData

    //В MyApp или даггер?
    private val db = Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoryInfoData").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    private val repositoryInfoDao = db.getRepositoryInfoDao()

    fun loadData(userName: String, callbackList: (list: List<RepositoryInfoData>) -> Unit) {

        val localListData = repositoryInfoDao.getUser(userName)

        Log.d("TAG123321", "AppDatabase loadData")

        if (!localListData.isNullOrEmpty())
            callbackList(localListData)
//            _liveData.value = localListData
        else
            throw NoDataInDatabaseException()
    }

    fun updateData(userName: String, list: List<RepositoryInfoData>) {
        Log.d("TAG123321", "updateData userName = $userName")
        repositoryInfoDao.delete(userName)
        repositoryInfoDao.insert(list)

        //        repositoryInfoDao.updateUserData(userName)
    }

}