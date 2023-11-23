package com.example.employeeleaveapp.calendar.model

import android.os.Parcelable
import com.example.employeeleaveapp.data.TypeOfLeave
import kotlinx.parcelize.Parcelize

@Parcelize
data class Leave(
    val id: Int = 0,
    val email: String,
    val startDate: Long,
    val endDate: Long,
    val typeOfLeave: TypeOfLeave,
) : Parcelable