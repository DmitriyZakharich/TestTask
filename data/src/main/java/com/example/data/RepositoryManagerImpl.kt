package com.example.data

import com.example.data.interfaces.AppDatabase
import com.example.data.interfaces.NetworkLoader
import com.example.data.mappers.mapToDomain
import com.example.data.models.UserDetailsData
import com.example.data.response.DetailsResponseResult
import com.example.data.response.ListResponseResult
import com.example.data.utils.ConnectionManager
import com.example.domain.models.UserDetails
import com.example.domain.models.UserShort
import com.example.domain.repository.RepositoryManager

class RepositoryManagerImpl(
    private val networkLoader: NetworkLoader,
    private val appDatabase: AppDatabase,
    private val connectionManager: ConnectionManager
) : RepositoryManager {

    override suspend fun getUsersList(): List<UserShort> {

        if (!connectionManager.isConnected()) {
            return appDatabase.loadUsersList().mapToDomain()
        }

        return when (val responseResult = networkLoader.loadUsersList()) {
            is ListResponseResult.Success -> {
                if (responseResult.list.isNotEmpty()) {
                    appDatabase.insertUserShortData(responseResult.list)
                    responseResult.list.mapToDomain()
                } else {
                    appDatabase.loadUsersList().mapToDomain()
                }
            }
            ListResponseResult.Failure -> {
                return appDatabase.loadUsersList().mapToDomain()
            }
        }
    }

    override suspend fun getUserDetails(login: String): UserDetails? {
        if (!connectionManager.isConnected()) {
            return appDatabase.loadUserDetailsData(login)?.mapToDomain()
        }

        return when (val responseResult = networkLoader.loadUserDetailsData(login = login)) {
            is DetailsResponseResult.Success -> {
                appDatabase.insertUserDetailsData(data = responseResult.data)
                responseResult.data.mapToDomain()
            }
            DetailsResponseResult.Failure -> appDatabase.loadUserDetailsData(login)?.mapToDomain()
        }
    }
}