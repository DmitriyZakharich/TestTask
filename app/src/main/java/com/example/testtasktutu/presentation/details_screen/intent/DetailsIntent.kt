package com.example.testtasktutu.presentation.details_screen.intent

sealed class DetailsIntent {
    data class FetchDetails(val login: String) : DetailsIntent()
}
