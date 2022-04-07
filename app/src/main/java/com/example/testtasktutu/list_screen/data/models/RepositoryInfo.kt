package com.example.testtasktutu.list_screen.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoryInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    val name: String?,
    val description: String?,
    val language: String?,
    val stargazers_count: Int?,
    val updated_at: String?
)
