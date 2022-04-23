package com.example.testtasktutu.screens.common_interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.app_data.models.ParcelDetailsInfo
import com.example.testtasktutu.app_data.models.ParcelRepositoriesInfo

interface RepositoriesInfoLoader {
    val parcelDetailsInfo: LiveData<ParcelDetailsInfo>
    val parcelRepositoryInfo: LiveData<ParcelRepositoriesInfo>
    fun loadRepositoriesList(query: String)
    fun loadRepositoryInfo(login: String, name: String)
}