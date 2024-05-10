package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.UserDetailsData
import com.example.data.models.UserShortData

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM users_short_data")
    fun getListUsers(): List<UserShortData>?

    @Query("SELECT * FROM users_details_data WHERE login = :login")
    fun getUserDetails(login: String): UserDetailsData

    @Query("DELETE FROM users_short_data WHERE login = :login")
    fun deleteUsersShortData(login: String)

    @Query("DELETE FROM users_details_data WHERE login = :login")
    fun deleteUsersDetailsData(login: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsersShortData(list: List<UserShortData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsersDetailsData(data: UserDetailsData)
}