package com.example.employeeleaveapp.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.employeeleaveapp.ui.theme.EmployeeLeaveDestination

@Composable
fun NavBar(
    allScreens: List<EmployeeLeaveDestination>,
    onTabSelected: (EmployeeLeaveDestination) -> Unit,
    currentScreen: EmployeeLeaveDestination
) {
    NavigationBar {
        allScreens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon ?: Icons.Outlined.WarningAmber,
                        contentDescription = "${screen.title} Icon"
                    )
                },
                selected = currentScreen == screen,
                onClick = {onTabSelected(screen)},
                label = { Text(text = screen.title ?: " ") },
            )
        }
    }
}
