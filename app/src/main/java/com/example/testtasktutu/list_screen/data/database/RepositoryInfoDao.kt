package com.example.testtasktutu.list_screen.data.database

import androidx.room.*
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData


@Dao
interface RepositoryInfoDao {
    @Query("SELECT * FROM repositoryinfodata WHERE login = :login")
    fun getUser(login: String): List<RepositoryInfoData>?

    @Query("DELETE FROM repositoryinfodata WHERE login = :login")
    fun delete(login: String)

    @Insert
    fun insert(list: List<RepositoryInfoData>)
}
