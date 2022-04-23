package com.example.testtasktutu.screens.list_screen.domain.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.screens.list_screen.domain.models.ParcelRepositoryBriefInfo

interface DataManager {
    val data: LiveData<ParcelRepositoryBriefInfo>
    fun getData(login: String)
}
