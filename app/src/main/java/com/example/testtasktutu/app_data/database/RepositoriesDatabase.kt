package com.example.testtasktutu.app_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtasktutu.app_data.models.RepositoriesInfoData

@Database(entities = [RepositoriesInfoData::class], version = 15)
abstract class RepositoriesDatabase : RoomDatabase() {
    abstract fun getRepositoryInfoDao(): RepositoryInfoDao
}