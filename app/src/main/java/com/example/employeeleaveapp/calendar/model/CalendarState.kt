package com.example.employeeleaveapp.calendar.model

data class CalendarState(
    val userName: String = "",
    val leaveEntityList: List<Leave> = emptyList()
)