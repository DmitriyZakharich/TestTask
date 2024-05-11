package com.example.data.interfaces

import com.example.data.models.UserDetailsData
import com.example.data.models.UserShortData
import com.example.data.response.DetailsResponseResult
import com.example.data.response.ListResponseResult

interface NetworkLoader {
    suspend fun loadUsersList(): ListResponseResult
    suspend fun loadUserDetailsData(login: String): DetailsResponseResult
}