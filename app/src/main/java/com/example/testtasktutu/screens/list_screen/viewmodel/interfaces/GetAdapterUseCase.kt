package com.example.testtasktutu.screens.list_screen.viewmodel.interfaces

import com.example.testtasktutu.screens.list_screen.domain.CustomRecyclerAdapter

interface GetAdapterUseCase {

    suspend fun start(query: String): CustomRecyclerAdapter
}