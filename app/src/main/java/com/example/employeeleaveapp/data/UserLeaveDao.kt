package com.example.employeeleaveapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface UserLeaveDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)


    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUser(email: String? = "", password: String): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUser(email: String? = ""): User?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLeave(leave: LeaveEntity)

    @Query("SELECT * FROM users,leave WHERE startDate <=  :selectedDate AND endDate >= :selectedDate AND users.email = leave.email")
    fun getUserLeave(selectedDate: Date): Flow<List<UserLeaveEntity>>

    @Query("SELECT * FROM leave WHERE startDate <=  :selectedDate AND endDate >= :selectedDate")
    fun getLeave(selectedDate: Date): Flow<List<LeaveEntity>>
}