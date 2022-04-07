package com.example.testtasktutu.list_screen.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [RepositoryInfoDao::class], version = 1)
abstract class RepositoriesDatabase() : RoomDatabase() {
}