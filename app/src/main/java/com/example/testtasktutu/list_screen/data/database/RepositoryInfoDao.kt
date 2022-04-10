package com.example.testtasktutu.list_screen.data.database

import androidx.room.*
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData


@Dao
interface RepositoryInfoDao {
//    @Query("SELECT * FROM repositoryinfodata")
//    fun getAll(): List<RepositoryInfoData>

    @Query("SELECT * FROM repositoryinfodata WHERE username = :userName")
    fun getUser(userName: String): List<RepositoryInfoData>?

//    @Query("INSERT * FROM repositoryinfodata WHERE username = :userName")
//    fun updateUserData(userName: String)

    @Insert
    fun insert(list: List<RepositoryInfoData>)

//    @Update
//    fun update(repositoryInfoData: RepositoryInfoData)

    @Query("DELETE FROM repositoryinfodata WHERE username = :userName")
    fun delete(userName: String)
}