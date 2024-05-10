package com.example.data.mappers

import com.example.data.models.UserDetailsData
import com.example.domain.models.UserDetails

fun UserDetailsData.mapToDomain() = UserDetails(
    id = id,
    name = name,
    login = login,
    avatarUrl = avatarUrl,
    createdAt = createdAt,
    location = location
)