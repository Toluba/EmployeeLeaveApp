package com.example.employeeleaveapp.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.employeeleaveapp.BottomNav.NavBar
import com.example.employeeleaveapp.ui.theme.BlueDark
import com.example.employeeleaveapp.ui.theme.BlueLight
import com.example.employeeleaveapp.ui.theme.Circle

//TODO - fix days balance positioning
//TODO - Fix navigations and onclicks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {},
        bottomBar = {
            NavBar()
        },
        content = { Box(modifier = Modifier.padding(it))

            RemainingLeave(heading = "Leave Balance") { }
        }
    )


}

@Composable
fun RemainingLeave(
    modifier: Modifier = Modifier,
    heading: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(16.dp)
        ) {
            Text(text = heading, fontSize = 20.sp, color = Color.Black)
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = modifier.padding(start = 160.dp),
            )
        }
        Divider(thickness = 1.dp, color = Color.LightGray)
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BalanceRemainingCircle(BASIC_TOTAL_LEAVE, USED_LEAVE_EXAMPLE)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Circle(BlueLight)
                Text("Used $USED_LEAVE_EXAMPLE days ", modifier.padding(start = 5.dp))

                Spacer(modifier = modifier.padding(10.dp))

                Circle(BlueDark)
                Text(
                    "Available $REMAINING_LEAVE_EXAMPLE days",
                    modifier.padding(start = 5.dp)
                )

            }
        }
    }
}


@Composable
fun MyRequests(
    modifier: Modifier = Modifier,
    heading: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    ) {

    }
}

@Preview
@Composable
fun RemainingLeavePreview() {
    RemainingLeave(
        heading = "Leave Balance",
        onClick = {}
    )
}


@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}