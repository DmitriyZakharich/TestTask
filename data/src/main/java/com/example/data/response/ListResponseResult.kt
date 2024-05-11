package com.example.data.response

import com.example.data.models.UserShortData

sealed interface ListResponseResult {
    data class Success(val list: List<UserShortData>): ListResponseResult
    data object Failure: ListResponseResult
}