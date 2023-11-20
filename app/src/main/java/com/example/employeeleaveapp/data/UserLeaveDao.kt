package com.example.employeeleaveapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.Date

@Dao
interface UserLeaveDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)


    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUser(email: String? = "", password: String): User?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLeave(leave: Leave)

    @Query("SELECT * FROM leave WHERE startDate >=  :startDate AND endDate <= :endDate")
    suspend fun getLeave(startDate: Date, endDate: Date): List<Leave>
}