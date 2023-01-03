package com.example.testtasktutu.app_repository.database

import androidx.room.Room
import com.example.testtasktutu.MyApp

object DatabaseHelper {

    fun getDao(): GithubRepoInfoDao {
        val db = Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoriesInfoData").fallbackToDestructiveMigration().build()
        return db.getRepositoryInfoDao()
    }
}