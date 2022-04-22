package com.example.testtasktutu.list_screen.domain.interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.app_data.models.ParcelDetailsInfo
import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData

interface RepositoriesInfoLoader {
    val parcelDetailsInfo: LiveData<ParcelDetailsInfo>
    fun loadRepositoriesList(query: String, callbackList: (isSuccess: Boolean, login: String, list: List<RepositoryBriefInfoData>?) -> Unit)
    fun loadRepositoryInfo(login: String, name: String)
}