package com.example.employeeleaveapp.data.mapper

import com.example.employeeleaveapp.calendar.model.Leave
import com.example.employeeleaveapp.data.LeaveEntity



fun LeaveEntity.toLeave(): Leave {
    return Leave(
        id = id,
        email = email,
        startDate = startDate,
        endDate = endDate,
        typeOfLeave = typeOfLeave,
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
