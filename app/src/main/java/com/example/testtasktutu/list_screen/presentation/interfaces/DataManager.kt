package com.example.testtasktutu.list_screen.presentation.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.list_screen.domain.models.ParcelRepositoryBriefInfo

interface DataManager {
    val data: LiveData<ParcelRepositoryBriefInfo>
    fun getData(login: String)
}
