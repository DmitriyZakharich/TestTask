package com.example.testtasktutu.screens.details_screen.presentation.intent

sealed class DetailsIntent {
    data class FetchDetails(val login: String, val name: String) : DetailsIntent()
}
