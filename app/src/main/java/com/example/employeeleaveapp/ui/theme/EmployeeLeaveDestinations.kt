package com.example.employeeleaveapp.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

interface EmployeeLeaveDestination {
    val title: String
    val icon: ImageVector?
    val route: String
    val bottomBarVisibility: Boolean
    val topBarVisibility: Boolean
}

object Home : EmployeeLeaveDestination {
    override val title = "Home"
    override val icon = Icons.Filled.Home
    override val route = "home"
    override val bottomBarVisibility = true
    override val topBarVisibility = true
}

object Request : EmployeeLeaveDestination {
    override val title = "Requests"
    override val icon = Icons.Filled.Create
    override val route = "requests"
    override val bottomBarVisibility = true
    override val topBarVisibility = true

}

object Calendar : EmployeeLeaveDestination {
    override val title = "Calender"
    override val icon = Icons.Filled.DateRange
    override val route = "teamcalendar"
    override val bottomBarVisibility = true
    override val topBarVisibility = true

}

object Settings : EmployeeLeaveDestination {
    override val title = "Settings"
    override val icon = Icons.Filled.Settings
    override val route = "settings"
    override val bottomBarVisibility =true
    override val topBarVisibility = true

}

object Login : EmployeeLeaveDestination {
    override val title = ""
    override val icon = null
    override val route: String = "login"
    override val bottomBarVisibility = false
    override val topBarVisibility = false
}

object SignUp : EmployeeLeaveDestination {
    override val title = ""
    override val icon = null
    override val route: String = "signup"
    override val bottomBarVisibility = false
    override val topBarVisibility = false
}

val employeeLeaveBottomBar = listOf(Home, Request, Calendar, Settings)


