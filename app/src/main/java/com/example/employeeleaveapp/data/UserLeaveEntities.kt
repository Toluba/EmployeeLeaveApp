package com.example.employeeleaveapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String?="",
)

@Entity(tableName = "leave")
data class LeaveEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val startDate: Long,
    val endDate: Long,
    val typeOfLeave: TypeOfLeave,
)


data class UserLeaveEntity(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String?="",
    val id: Int = 0,
    val startDate: Long,
    val endDate: Long,
    val typeOfLeave: TypeOfLeave,
)

enum class TypeOfLeave{
    Annual, Sick, KDay
}