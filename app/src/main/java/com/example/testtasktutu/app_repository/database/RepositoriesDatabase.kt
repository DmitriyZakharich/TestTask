package com.example.testtasktutu.app_repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData

@Database(entities = [GithubRepoInfoData::class], version = 19)
abstract class RepositoriesDatabase : RoomDatabase() {
    abstract fun getRepositoryInfoDao(): GithubRepoInfoDao
}