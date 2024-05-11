package com.example.data.response

import com.example.data.models.UserDetailsData

sealed interface DetailsResponseResult {
    data class Success(val data: UserDetailsData): DetailsResponseResult
    data object Failure: DetailsResponseResult
}