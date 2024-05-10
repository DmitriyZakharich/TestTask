package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users_details_data")
data class UserDetailsData(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val login: String?,
    @ColumnInfo(name = "avatar_url")
    @SerializedName(value = "avatar_url")
    val avatarUrl: String?,
    @ColumnInfo(name = "created_at")
    @SerializedName(value = "created_at")
    val createdAt: String?,
    val location: String?
)