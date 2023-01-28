package com.example.testtasktutu.screens.list_screen.presentation.intent

sealed class ListIntent {
    data class FetchUser(val query: String) : ListIntent()
}
