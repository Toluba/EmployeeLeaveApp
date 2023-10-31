package com.example.employeeleaveapp.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SettingScreen(){
    Box(modifier = Modifier.background(color = Color.Green)){
        Text(
            text = "Setting Screen ",
            style = MaterialTheme.typography.displayLarge
        )
    }
}