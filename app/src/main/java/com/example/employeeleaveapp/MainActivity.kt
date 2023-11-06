package com.example.employeeleaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.employeeleaveapp.bottomNav.NavBar
import com.example.employeeleaveapp.calendar.CalendarScreen
import com.example.employeeleaveapp.home.HomeScreen
import com.example.employeeleaveapp.navigation.TopBar
import com.example.employeeleaveapp.requests.RequestScreen
import com.example.employeeleaveapp.settings.SettingScreen
import com.example.employeeleaveapp.ui.theme.Calendar
import com.example.employeeleaveapp.ui.theme.EmployeeLeaveAppTheme
import com.example.employeeleaveapp.ui.theme.Home
import com.example.employeeleaveapp.ui.theme.Request
import com.example.employeeleaveapp.ui.theme.Settings
import com.example.employeeleaveapp.ui.theme.employeeLeaveBottomBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeLeaveApp()
        }
    }
    //TODO - move navigation from mainActivity into NavHost File
    //'TODO - move

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EmployeeLeaveApp() {
        EmployeeLeaveAppTheme {
            val navController = rememberNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentDestination = currentBackStack?.destination
            val currentScreen =
                employeeLeaveBottomBar.find { it.route == currentDestination?.route } ?: Home

            Scaffold(
                modifier = Modifier,
                bottomBar = {
                    NavBar(
                        allScreens = employeeLeaveBottomBar,
                        onTabSelected = { newScreen ->
                            navController.navigateSingleTopTo(route = newScreen.route)
                        },
                        currentScreen = currentScreen,
                    )
                },
                topBar = {
                         TopBar()
//                    CenterAlignedTopAppBar(
//                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                            containerColor = MaterialTheme.colorScheme.primaryContainer,
//                            titleContentColor = MaterialTheme.colorScheme.primary,
//                        ),
//                        title = {Text("Home") }
//                    )
                },

                ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Home.route,
                    modifier = Modifier.padding(innerPadding),
                ) {
                    composable(route = Home.route) {
                        HomeScreen()
                    }
                    composable(route = Request.route) {
                        RequestScreen()
                    }
                    composable(route = Calendar.route) {
                        CalendarScreen()
                    }
                    composable(route = Settings.route) {
                        SettingScreen()
                    }
                }

            }
        }
    }

    fun NavHostController.navigateSingleTopTo(route: String) =
        this.navigate(route) {
            popUpTo(
                this@navigateSingleTopTo.graph.findStartDestination().id
            ) {
                saveState = true
            }
            launchSingleTop = true
        }
}
