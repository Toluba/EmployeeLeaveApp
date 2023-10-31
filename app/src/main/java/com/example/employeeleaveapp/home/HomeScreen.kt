package com.example.employeeleaveapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import com.example.employeeleaveapp.ui.theme.BlueDark
import com.example.employeeleaveapp.ui.theme.BlueLight
import com.example.employeeleaveapp.ui.theme.Circle

//TODO - fix days balance positioning
//TODO - Fix navigation and onClicks

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        RemainingLeaveCard()
        Spacer(Modifier.height(16.dp))
        MyRequestsCard()

    }
}


@Composable
fun RemainingLeaveCard(
    modifier: Modifier = Modifier,
    //onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            //.clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(16.dp)
        ) {
            Text(text = "Leave Balance", fontSize = 20.sp, color = Color.Black)
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
fun MyRequestsCard(
    modifier: Modifier = Modifier,
    //onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            //.clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(16.dp)
        ) {
            Text(text = "Upcoming Requests", fontSize = 20.sp, color = Color.Black)
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = modifier.padding(start = 160.dp),
            )
        }
    }
}

@Preview
@Composable
fun RemainingLeavePreview() {
    RemainingLeaveCard()
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview
@Composable
fun MyRequestsPreview() {
    MyRequestsCard()
}