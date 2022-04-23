package com.example.testtasktutu.list_screen.domain.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.app_data.models.ParcelDetailsInfo
import com.example.testtasktutu.app_data.models.ParcelRepositoriesInfo
import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData

interface RepositoriesInfoLoader {
    val parcelDetailsInfo: LiveData<ParcelDetailsInfo>
    val parcelRepositoryInfo: LiveData<ParcelRepositoriesInfo>
    fun loadRepositoriesList(query: String)
    fun loadRepositoryInfo(login: String, name: String)
}