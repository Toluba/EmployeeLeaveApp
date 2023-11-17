package com.example.employeeleaveapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String?="",
)

@Entity(tableName = "leave")
data class Leave(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val startDate: Date,
    val endDate: Date,
    val typeOfLeave: TypeOfLeave,
)

enum class TypeOfLeave{
    Annual, Sick, KDay
}