package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.UserDetailsData
import com.example.data.models.UserShortData

@Database(entities = [UserShortData::class, UserDetailsData::class], version = 22)
abstract class RepositoriesDatabase : RoomDatabase() {
    abstract fun getUsersDataDao(): DatabaseDao
}