package com.example.testtasktutu.list_screen.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoryBriefInfoData(
    var login: String?,
    var name: String?,  //repository name
    var description: String?,
    var language: String?,
    var stargazers_count: Int?,
    var updated_at: String?
){
    @PrimaryKey(autoGenerate = true)
     var id: Int = 0
}
