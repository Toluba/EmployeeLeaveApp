package com.example.employeeleaveapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.employeeleaveapp.calendar.CalendarScreen
import com.example.employeeleaveapp.home.HomeScreen
import com.example.employeeleaveapp.login.LoginScreen
import com.example.employeeleaveapp.login.UserLeaveViewModel
import com.example.employeeleaveapp.requests.RequestScreen
import com.example.employeeleaveapp.settings.SettingScreen
import com.example.employeeleaveapp.ui.theme.Calendar
import com.example.employeeleaveapp.ui.theme.Home
import com.example.employeeleaveapp.ui.theme.Login
import com.example.employeeleaveapp.ui.theme.Request
import com.example.employeeleaveapp.ui.theme.Settings

@Composable
fun EmployeeLeaveAppNavHost(
    navController: NavHostController,
    bottomBarVisibility: MutableState<Boolean>,
    topBarVisibility: MutableState<Boolean>,
    topBarText: MutableState<String>,
    modifier: Modifier,
    userLeaveViewModel: UserLeaveViewModel = viewModel(factory = UserLeaveViewModel.Factory)
){
    NavHost(
        navController = navController,
        startDestination = Login.route,
        modifier = modifier,
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
            CalendarScreen(userLeaveViewModel)
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
