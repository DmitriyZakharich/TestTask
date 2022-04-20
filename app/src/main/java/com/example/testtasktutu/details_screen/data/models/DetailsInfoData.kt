package com.example.testtasktutu.details_screen.data.models

import androidx.room.PrimaryKey

data class DetailsInfoData(
        var login: String?,
        var name: String,   //repository name
        var description: String?,
        var language: String?,
        var stargazers_count: Int?,
        var updated_at: String?
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
