package com.example.employeeleaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.employeeleaveapp.Navigation.NavBar
import com.example.employeeleaveapp.Navigation.TopBar
import com.example.employeeleaveapp.ui.theme.EmployeeLeaveAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeLeaveAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { TopBar("Home")},
                    bottomBar = { NavBar() },
                ){
                    Box(modifier = Modifier.padding(it))
                }
            }
        }
    }
}
