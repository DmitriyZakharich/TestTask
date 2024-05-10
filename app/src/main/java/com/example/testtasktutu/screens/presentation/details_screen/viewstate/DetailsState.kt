package com.example.testtasktutu.screens.presentation.details_screen.viewstate

import com.example.domain.models.UserDetails

sealed class DetailsState {
    data object Idle : DetailsState()
    data object Loading : DetailsState()
    data class Details(val data: UserDetails) : DetailsState()
    data class Error(val error: String?) : DetailsState()
}