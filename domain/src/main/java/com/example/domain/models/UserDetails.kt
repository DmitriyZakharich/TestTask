package com.example.domain.models

data class UserDetails(
    val id: Int?,
    var login: String? = "",
    var name: String? = "",
    val avatarUrl: String? = "",
    val createdAt: String? = "",
    val location: String? = ""
)
