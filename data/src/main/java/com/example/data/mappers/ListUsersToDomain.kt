package com.example.data.mappers

import com.example.data.models.UserShortData
import com.example.domain.models.UserShort

fun List<UserShortData>.mapToDomain(): List<UserShort> {
    val newList = MutableList<UserShort>(this.size) { UserShort() }
    this.forEachIndexed { index, data ->
        newList[index] = UserShort(
            login = data.login,
            id = data.id,
            avatarUrl = data.avatarUrl
        )
    }
    return newList
}