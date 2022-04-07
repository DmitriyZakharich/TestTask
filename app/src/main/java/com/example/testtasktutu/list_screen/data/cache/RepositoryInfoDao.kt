package com.example.testtasktutu.list_screen.data.cache

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RepositoryInfoDao {
    @Query("SELECT * FROM todoentity WHERE title LIKE :title")
    fun findByTitle(title: String): TodoEntity
}