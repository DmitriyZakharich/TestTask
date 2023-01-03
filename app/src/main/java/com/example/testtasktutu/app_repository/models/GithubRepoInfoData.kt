package com.example.testtasktutu.app_repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GithubRepoInfoData(
        var login: String? = "",
        var name: String? = "",  //repository name
        var description: String? = "",
        var language: String? = "",
        var stargazers_count: Int? = 0,
        var updated_at: String? = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun GithubRepoInfoData.isNotEmpty() =
    login != "" && name != ""

