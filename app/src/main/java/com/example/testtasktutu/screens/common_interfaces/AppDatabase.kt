package com.example.testtasktutu.screens.common_interfaces

import androidx.lifecycle.LiveData
import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData

interface AppDatabase {
    val repositoriesList: LiveData<List<RepositoryBriefInfoData>?>
    val repositoryInfo: LiveData<RepositoriesInfoData?>
    fun loadRepositoriesList(login: String)
    fun updateData(login: String, listInsert: List<RepositoriesInfoData>)
    fun loadRepositoryInfo(login: String, name: String)
    fun updateData(detailsInfoData: RepositoriesInfoData)
}