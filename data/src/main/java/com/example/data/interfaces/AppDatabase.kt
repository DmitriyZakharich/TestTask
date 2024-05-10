package com.example.data.interfaces

import com.example.data.models.UserDetailsData
import com.example.data.models.UserShortData

interface AppDatabase {
    suspend fun loadUsersList(): List<UserShortData>
    suspend fun loadUserDetailsData(login: String): UserDetailsData
    suspend fun insertUserShortData(list: List<UserShortData>)
    suspend fun insertUserDetailsData(data: UserDetailsData)
}