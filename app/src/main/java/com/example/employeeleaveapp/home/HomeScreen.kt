package com.example.employeeleaveapp.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val DividerLengthInDegrees = 1.8f

@Composable
fun HomeScreen() {

}

@Composable
fun RemainingLeave(
    modifier: Modifier = Modifier,
    heading: String,
    onClick: () -> Unit
) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onClick() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = heading, fontSize = 20.sp, color = Color.Black)
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = modifier.padding(start = 160.dp),
                )
            }
            DonutChart(26, 5)

        }
    }



@Composable
fun MyRequestsS() {

}

@Preview
@Composable
fun RemainingLeavePreview(showSystemUi: Boolean = true) {
    RemainingLeave(
        heading = "Leave Balance",
        onClick = {}
    )
}