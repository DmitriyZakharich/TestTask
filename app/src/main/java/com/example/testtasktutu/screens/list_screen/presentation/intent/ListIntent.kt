package com.example.testtasktutu.screens.list_screen.presentation.intent

import android.os.Bundle

sealed class ListIntent {
    data class FetchUser(val query: String) : ListIntent()
    data class Navigate(val bundle: Bundle) : ListIntent()
    object RestoreState : ListIntent()
}
