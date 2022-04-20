package com.example.testtasktutu.list_screen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtasktutu.list_screen.data.models.RepositoryBriefInfoData

@Database (entities = [RepositoryBriefInfoData::class], version = 12)
abstract class RepositoriesDatabase : RoomDatabase() {
    abstract fun getRepositoryInfoDao(): RepositoryInfoDao
}