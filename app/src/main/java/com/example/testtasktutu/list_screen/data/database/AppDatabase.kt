package com.example.testtasktutu.list_screen.data.database

import android.util.Log
import androidx.room.Room
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataInDatabaseException
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData

class AppDatabase {

    //В MyApp или даггер?
    //Добавить ассинхронность
    private val db = Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoryInfoData").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    private val repositoryInfoDao = db.getRepositoryInfoDao()

    fun loadData(login: String, callbackList: (List<RepositoryInfoData>?) -> Unit) {
        callbackList(repositoryInfoDao.getUser(login))
    }

    fun updateData(login: String, listInsert: List<RepositoryInfoData>) {
        repositoryInfoDao.delete(login)
        repositoryInfoDao.insert(listInsert)
    }
}