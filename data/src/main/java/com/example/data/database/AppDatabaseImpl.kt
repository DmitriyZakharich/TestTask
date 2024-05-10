package com.example.data.database

import com.example.data.interfaces.AppDatabase
import com.example.data.models.UserDetailsData
import com.example.data.models.UserShortData

class AppDatabaseImpl (private val database: DatabaseHelper) : AppDatabase {

    override suspend fun loadUsersList(): List<UserShortData> {
            return database.getDao().getListUsers() ?: emptyList()
    }

    override suspend fun loadUserDetailsData(login: String): UserDetailsData {
            return database.getDao().getUserDetails(login)
    }

    override suspend fun insertUserShortData(list: List<UserShortData>) {
        database.getDao().insertUsersShortData(list = list)
    }

    override suspend fun insertUserDetailsData(data: UserDetailsData) {
        database.getDao().insertUsersDetailsData(data = data)
    }
}