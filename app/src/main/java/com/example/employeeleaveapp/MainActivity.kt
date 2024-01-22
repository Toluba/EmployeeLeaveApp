package com.example.employeeleaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.employeeleaveapp.bottomNav.NavBar
import com.example.employeeleaveapp.navigation.EmployeeLeaveAppNavHost
import com.example.employeeleaveapp.ui.theme.EmployeeLeaveAppTheme
import com.example.employeeleaveapp.ui.theme.Home
import com.example.employeeleaveapp.ui.theme.employeeLeaveBottomBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeLeaveApp()
        }
    }

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
                EmployeeLeaveAppNavHost(
                    navController = navController,
                    bottomBarVisibility = bottomBarVisibility,
                    topBarVisibility = topBarVisibility,
                    topBarText = topBarText,
                    modifier = Modifier.padding(innerPadding)
                )
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
