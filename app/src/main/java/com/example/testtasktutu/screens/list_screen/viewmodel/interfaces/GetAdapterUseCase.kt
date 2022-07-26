package com.example.testtasktutu.screens.list_screen.viewmodel.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.list_screen.domain.CustomRecyclerAdapter

interface GetAdapterUseCase {
    var adapter: LiveData<CustomRecyclerAdapter>
    fun start(query: String)
}