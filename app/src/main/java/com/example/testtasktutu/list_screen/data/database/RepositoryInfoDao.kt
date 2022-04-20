package com.example.testtasktutu.list_screen.data.database

import androidx.room.*
import com.example.testtasktutu.list_screen.data.models.RepositoryBriefInfoData


@Dao
interface RepositoryInfoDao {
    @Query("SELECT * FROM RepositoryBriefInfoData WHERE login = :login")
    fun getUser(login: String): List<RepositoryBriefInfoData>?

    @Query("DELETE FROM RepositoryBriefInfoData WHERE login = :login")
    fun delete(login: String)

    @Insert
    fun insert(list: List<RepositoryBriefInfoData>)
}
