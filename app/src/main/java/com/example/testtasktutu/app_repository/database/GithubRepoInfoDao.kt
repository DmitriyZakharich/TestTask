package com.example.testtasktutu.app_repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData

@Dao
interface GithubRepoInfoDao {
    @Query("SELECT * FROM GithubRepoInfoData WHERE login = :login")
    fun getGithubReposList(login: String): List<GithubRepoBriefInfoData>?

    @Query("SELECT * FROM GithubRepoInfoData WHERE login = :login AND name = :name")
    fun getGithubRepoInfo(login: String, name: String): GithubRepoInfoData

    @Query("DELETE FROM GithubRepoInfoData WHERE login = :login")
    fun delete(login: String)

    @Insert
    fun insert(list: List<GithubRepoInfoData>)

    @Update
    fun updateRepositoryInfo(repos: GithubRepoInfoData)
}