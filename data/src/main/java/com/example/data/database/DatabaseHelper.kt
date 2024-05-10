package com.example.data.database

import android.content.Context
import androidx.room.Room

class DatabaseHelper(private val appContext: Context) {

    fun getDao(): DatabaseDao {
        val db = Room.databaseBuilder(
            appContext, RepositoriesDatabase::class.java,
            "RepositoriesDatabase"
        ).fallbackToDestructiveMigration().build()
        return db.getUsersDataDao()
    }
}