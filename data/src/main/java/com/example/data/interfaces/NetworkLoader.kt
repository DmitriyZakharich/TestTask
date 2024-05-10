package com.example.data.interfaces

import com.example.data.models.UserDetailsData
import com.example.data.models.UserShortData

interface NetworkLoader {
    suspend fun loadUsersList(): List<UserShortData>
    suspend fun loadUserDetailsData(login: String): UserDetailsData
}