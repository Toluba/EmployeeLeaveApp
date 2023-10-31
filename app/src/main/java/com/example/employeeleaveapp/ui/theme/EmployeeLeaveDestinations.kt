package com.example.employeeleaveapp.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

interface EmployeeLeaveDestination {
    val title: String
    val icon: ImageVector
    val route: String
}

object Home : EmployeeLeaveDestination {
    override val title = "Home"
    override val icon = Icons.Filled.Home
    override val route = "home"
}

object Request : EmployeeLeaveDestination {
    override val title = "Requests"
    override val icon = Icons.Filled.Create
    override val route = "requests"

}

object Calendar : EmployeeLeaveDestination {
    override val title = "Calender"
    override val icon = Icons.Filled.DateRange
    override val route = "teamcalendar"

}

object Settings : EmployeeLeaveDestination {
    override val title = "Settings"
    override val icon = Icons.Filled.Settings
    override val route = "settings"

}


val employeeLeaveBottomBar = listOf(Home, Request, Calendar, Settings)


