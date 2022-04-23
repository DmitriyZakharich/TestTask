package com.example.testtasktutu.screens.details_screen.domain.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.details_screen.domain.model.RepositoriesInfoDomain

interface GetDataUseCase {
    val info: LiveData<RepositoriesInfoDomain>
    fun getData(login: String, name: String)
}