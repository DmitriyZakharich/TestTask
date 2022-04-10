package com.example.testtasktutu.list_screen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData

@Database (entities = [RepositoryInfoData::class], version = 3)
abstract class RepositoriesDatabase : RoomDatabase() {
    abstract fun getRepositoryInfoDao(): RepositoryInfoDao
}