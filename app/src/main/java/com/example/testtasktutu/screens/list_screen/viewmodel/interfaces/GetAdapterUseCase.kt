package com.example.testtasktutu.screens.list_screen.viewmodel.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.screens.list_screen.domain.models.RepositoryBriefInfoDomain

interface GetAdapterUseCase {
    var adapter: LiveData<CustomRecyclerAdapter>
    fun start(query: String, lambdaItemClick: (RepositoryBriefInfoDomain) -> Unit)
}