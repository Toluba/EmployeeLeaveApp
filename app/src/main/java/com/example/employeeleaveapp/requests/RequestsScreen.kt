package com.example.employeeleaveapp.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RequestScreen(){
    Box(modifier = Modifier.background(color = Color.Blue)){
        Text(
            text = "Request Screen",
            style = MaterialTheme.typography.displayLarge
        )
    }
}