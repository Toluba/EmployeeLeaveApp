package com.example.employeeleaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.employeeleaveapp.BottomNav.NavBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(title = {
        Text(
            text = "My Important Dates",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    })
}