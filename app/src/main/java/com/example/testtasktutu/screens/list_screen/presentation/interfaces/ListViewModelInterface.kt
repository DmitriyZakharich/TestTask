package com.example.testtasktutu.screens.list_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.screens.list_screen.domain.models.RepositoryBriefInfoDomain

interface ListViewModelInterface {
    val adapter: LiveData<CustomRecyclerAdapter>
    fun getAdapter(query: String, lambdaItemClick: (RepositoryBriefInfoDomain) -> Unit)
}