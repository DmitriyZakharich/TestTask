package com.example.data

import com.example.data.interfaces.AppDatabase
import com.example.data.interfaces.NetworkLoader
import com.example.data.mappers.mapToDomain
import com.example.data.models.UserDetailsData
import com.example.data.utils.ConnectionManager
import com.example.domain.models.UserShort
import com.example.domain.models.UserDetails
import com.example.domain.repository.RepositoryManager

class RepositoryManagerImpl(
    private val networkLoader: NetworkLoader,
    private val appDatabase: AppDatabase,
    private val connectionManager: ConnectionManager
) : RepositoryManager {

    override suspend fun getUsersList(): List<UserShort> {
        return if (connectionManager.isConnected()) {
            var list = networkLoader.loadUsersList()
            if (list.isNotEmpty()) {
                appDatabase.insertUserShortData(list)
            } else{
                list = appDatabase.loadUsersList()
            }
            list.mapToDomain()
        } else {
            val list = appDatabase.loadUsersList()
            list.mapToDomain()
        }
    }

    override suspend fun getUserDetails(login: String): UserDetails {
        return if (connectionManager.isConnected()) {
            val data = networkLoader.loadUserDetailsData(login = login)
            appDatabase.insertUserDetailsData(data = data)
            data.mapToDomain()
        } else {
            val info = appDatabase.loadUserDetailsData(login)
            info.mapToDomain()
        }
    }
}