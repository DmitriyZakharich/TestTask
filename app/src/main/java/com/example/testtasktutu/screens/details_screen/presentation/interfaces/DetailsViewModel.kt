package com.example.testtasktutu.screens.details_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.details_screen.domain.model.RepositoriesInfoDomain

interface DetailsViewModel {
    val infoData: LiveData<RepositoriesInfoDomain>
    fun getData(login: String, name: String)
}