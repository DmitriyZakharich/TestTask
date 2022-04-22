package com.example.testtasktutu.app_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData

@Dao
interface RepositoryInfoDao {
    @Query("SELECT * FROM RepositoriesInfoData WHERE login = :login")
    fun getRepositoriesList(login: String): List<RepositoryBriefInfoData>?

    @Query("SELECT * FROM RepositoriesInfoData WHERE login = :login AND name = :name")
    fun getRepositoryInfo(login: String, name: String): RepositoriesInfoData

    @Query("DELETE FROM RepositoriesInfoData WHERE login = :login")
    fun delete(login: String)

    @Insert
    fun insert(list: List<RepositoriesInfoData>)

    @Update
    fun updateRepositoryInfo(repos: RepositoriesInfoData)
}
