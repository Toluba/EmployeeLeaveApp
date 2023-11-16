package com.example.employeeleaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.employeeleaveapp.login.LoginScreen
import com.example.employeeleaveapp.login.UserViewModel
import com.example.employeeleaveapp.navigation.TopBar
import com.example.employeeleaveapp.requests.RequestScreen
import com.example.employeeleaveapp.settings.SettingScreen
import com.example.employeeleaveapp.ui.theme.Calendar
import com.example.employeeleaveapp.ui.theme.EmployeeLeaveAppTheme
import com.example.employeeleaveapp.ui.theme.Home
import com.example.employeeleaveapp.ui.theme.Login
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
            val bottomBarVisibility = rememberSaveable { (mutableStateOf(false)) }
            val topBarVisibility = rememberSaveable { (mutableStateOf(false)) }
            val topBarText = rememberSaveable { (mutableStateOf("")) }

            Scaffold(
                modifier = Modifier,
                bottomBar = {
                    AnimatedVisibility(
                        visible = bottomBarVisibility.value,
                        content = {
                            NavBar(
                                allScreens = employeeLeaveBottomBar,
                                onTabSelected = { newScreen ->
                                    navController.navigateSingleTopTo(route = newScreen.route)
                                },
                                currentScreen = currentScreen,
                            )
                        }
                    )
                },
                topBar = {
                    // TopBar()
                    AnimatedVisibility(
                        visible = topBarVisibility.value,
                        content = {
                            CenterAlignedTopAppBar(
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),
                                title = { Text(topBarText.value) }
                            )
                        }
                    )
                },

                ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Login.route,
                    modifier = Modifier.padding(innerPadding),
                    // bottomBarVisibility = bottomBarVisibility,
                ) {
                    composable(route = Login.route) {
                        LaunchedEffect(null) {
                            bottomBarVisibility.value = Login.bottomBarVisibility
                            topBarVisibility.value = Login.topBarVisibility
                            topBarText.value = Login.title
                        }
                        LoginScreen(navController = navController)
                    }
                    composable(route = Home.route) {
                        LaunchedEffect(null) {
                            bottomBarVisibility.value = Home.bottomBarVisibility
                            topBarVisibility.value = Home.topBarVisibility
                            topBarText.value = Home.title
                        }
                        HomeScreen()
                    }
                    composable(route = Request.route) {
                        LaunchedEffect(null) {
                            bottomBarVisibility.value = Request.bottomBarVisibility
                            topBarVisibility.value = Request.topBarVisibility
                            topBarText.value = Request.title
                        }
                        RequestScreen()
                    }
                    composable(route = Calendar.route) {
                        LaunchedEffect(null) {
                            bottomBarVisibility.value = Calendar.bottomBarVisibility
                            topBarVisibility.value = Calendar.topBarVisibility
                            topBarText.value = Calendar.title
                        }
                        CalendarScreen()
                    }
                    composable(route = Settings.route) {
                        LaunchedEffect(null) {
                            bottomBarVisibility.value = Settings.bottomBarVisibility
                            topBarVisibility.value = Settings.topBarVisibility
                            topBarText.value = Settings.title
                        }
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
