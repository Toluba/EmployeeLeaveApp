package com.example.employeeleaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.employeeleaveapp.ui.theme.EmployeeLeaveAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeLeaveAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                 bottomBar = {
                     NavBar()
                 }, content ={ Box(modifier = Modifier.padding(it))}
                )
            }
        }
    }
}

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = "home",
        icon = Icons.Filled.Home
    ),
    BottomNavItem(
        title = "Requests",
        route = "requests",
        icon = Icons.Filled.Create
    ),
    BottomNavItem(
        title = "Calender",
        route = "teamcalendar",
        icon = Icons.Filled.DateRange,
    ),
    BottomNavItem(
        title = "Settings",
        route = "settings",
        icon = Icons.Filled.Settings,
    ),
)

@Composable
fun NavBar() {

    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "${item.title} Icon"
                    )
                },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                label = { Text(text = item.title) },
            )
        }
    }
}



data class BottomNavItem(var title: String, var icon: ImageVector, var route: String)

@Preview(showBackground = true)
@Composable
fun NavPreview() {

}