package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users_details_data")
data class UserDetailsData(
    @PrimaryKey
    val id: Int? = null,
    val name: String? = null,
    val login: String? = null,
    @ColumnInfo(name = "avatar_url")
    @SerializedName(value = "avatar_url")
    val avatarUrl: String? = null,
    @ColumnInfo(name = "created_at")
    @SerializedName(value = "created_at")
    val createdAt: String? = null,
    val location: String? = null
)