package com.example.testtasktutu.screens.list_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.list_screen.domain.CustomRecyclerAdapter

interface ListViewModel {
    val adapter: LiveData<CustomRecyclerAdapter>
    fun getAdapter(query: String)
}