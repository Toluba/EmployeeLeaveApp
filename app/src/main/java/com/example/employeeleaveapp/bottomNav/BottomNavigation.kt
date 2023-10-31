package com.example.employeeleaveapp.bottomNav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
                        imageVector = screen.icon,
                        contentDescription = "${screen.title} Icon"
                    )
                },
                selected = currentScreen == screen,
                onClick = {onTabSelected(screen)},
                label = { Text(text = screen.title) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavPreview() {

}