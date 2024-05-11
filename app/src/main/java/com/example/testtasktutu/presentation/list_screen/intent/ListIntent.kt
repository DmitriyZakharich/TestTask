package com.example.testtasktutu.presentation.list_screen.intent

import android.os.Bundle

sealed class ListIntent {
    data object FetchList : ListIntent()
    data class Navigate(val bundle: Bundle) : ListIntent()
    data object RestoreState : ListIntent()
}
