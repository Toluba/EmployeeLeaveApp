package com.example.employeeleaveapp.data.mapper

import com.example.employeeleaveapp.calendar.model.Leave
import com.example.employeeleaveapp.data.LeaveEntity
import com.example.employeeleaveapp.data.UserLeaveEntity


fun UserLeaveEntity.toLeave(): Leave {
    return Leave(
        id = id,
        email = email,
        startDate = startDate,
        endDate = endDate,
        typeOfLeave = typeOfLeave,
        firstName = firstName,
        lastName = lastName
    )
}

fun Leave.toLeaveEntity(): LeaveEntity {
    return LeaveEntity(
        id = id,
        email = email,
        startDate = startDate,
        endDate = endDate,
        typeOfLeave = typeOfLeave,
    )
}
