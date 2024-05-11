package com.example.testtasktutu.presentation.list_screen.viewstate

import android.os.Bundle
import com.example.domain.models.UserShort

sealed class ListState {
    data object Idle : ListState()
    data object Loading : ListState()
    data class Repos(val repos: List<UserShort>) : ListState()
    data object NoData : ListState()
    data class Navigate(val bundle: Bundle) : ListState()
    data class Error(val error: String?) : ListState()
}
