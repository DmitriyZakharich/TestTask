package com.example.domain.repository

import com.example.domain.models.UserShort
import com.example.domain.models.UserDetails

interface RepositoryManager {
    suspend fun getUsersList(): List<UserShort>
    suspend fun getUserDetails(login: String): UserDetails?
}