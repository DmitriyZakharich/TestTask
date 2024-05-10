package com.example.testtasktutu.screens.presentation.details_screen.intent

sealed class DetailsIntent {
    data class FetchDetails(val login: String) : DetailsIntent()
}
