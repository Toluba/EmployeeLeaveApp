package com.example.employeeleaveapp.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.employeeleaveapp.ui.theme.Pink40

@Composable
fun CalendarScreen(){
    Box(modifier = Modifier.background(color = Pink40)){
        Text(
            text = "CalendarScreen",
            style = MaterialTheme.typography.displayLarge
        )
    }
}