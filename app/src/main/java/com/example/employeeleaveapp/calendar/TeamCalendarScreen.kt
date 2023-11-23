package com.example.employeeleaveapp.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.employeeleaveapp.calendar.data.CalendarDataSource
import com.example.employeeleaveapp.calendar.model.CalendarModel
import com.example.employeeleaveapp.extension.toFormattedDateShortString
import com.example.employeeleaveapp.extension.toFormattedDateString
import com.example.employeeleaveapp.extension.toFormattedMonthDateString
import com.example.employeeleaveapp.login.UserLeaveViewModel
import java.util.Calendar
import java.util.Date


@Composable
fun CalendarScreen(
    userLeaveViewModel: UserLeaveViewModel = viewModel(factory = UserLeaveViewModel.Factory),
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val viewState = userLeaveViewModel.leaves
//        DailyLeave(viewState, userLeaveViewModel::loadLeaveRequest)
        DailyLeave(userLeaveViewModel, userLeaveViewModel::loadLeaveRequest)

    }
}

@Composable
//fun DailyLeave(state: CalendarState, loadLeaveRequest: (Date) -> Unit) {
    fun DailyLeave(vm: UserLeaveViewModel, loadLeaveRequest: (Date) -> Unit) {
    val filteredLeaveRequestEntities by vm.leaves.collectAsState()


    DatesHeader { selectedDate ->
       // val newLeaveList = state.leaveEntityList
//        val newLeaveList = loadLeaveRequest()
//            .filter { leave ->
//                leave.startDate.toFormattedDateString() == selectedDate.date.toFormattedDateString()
//            }
//            .sortedBy { it.startDate }
        loadLeaveRequest(selectedDate.date)
//        filteredLeaveRequestEntities = newLeaveList
    }

    if (filteredLeaveRequestEntities.isEmpty()) {
        EmptyCard()
    } else {
        Column(
            modifier = Modifier,
        ) {
            filteredLeaveRequestEntities.forEach{
                LeaveCard(
                    leave = it,
                )
            }
        }
    }
}

@Composable
fun DatesHeader(
    onDateSelected: (CalendarModel.DateModel) -> Unit
) {
    val dataSource = CalendarDataSource()
    var calendarModel by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        DateHeader(
            data = calendarModel,
            onPrevClickListener = { startDate ->
                val calendar = Calendar.getInstance()
                calendar.time = startDate

                calendar.add(Calendar.DAY_OF_YEAR, -2)
                val finalStartDate = calendar.time

                calendarModel = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelectedDate = calendarModel.selectedDate.date
                )
            },
            onNextClickListener = { endDate ->
                val calendar = Calendar.getInstance()
                calendar.time = endDate

                calendar.add(Calendar.DAY_OF_YEAR, 2)
                val finalStartDate = calendar.time

                calendarModel = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelectedDate = calendarModel.selectedDate.date
                )
            }
        )
        DateList(
            data = calendarModel,
            onDateClickListener = { date ->
                calendarModel = calendarModel.copy(
                    selectedDate = date,
                    visibleDates = calendarModel.visibleDates.map {
                        it.copy(
                            isSelected = it.date.toFormattedDateString() == date.date.toFormattedDateString()
                        )
                    }
                )
                onDateSelected(date)
            }
        )
    }
}

@Composable
fun DateList(
    data: CalendarModel,
    onDateClickListener: (CalendarModel.DateModel) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(items = data.visibleDates) { date ->
            DateItem(date, onDateClickListener)
        }
    }
}

@Composable
fun DateItem(
    date: CalendarModel.DateModel,
    onClickListener: (CalendarModel.DateModel) -> Unit,
) {
    Column {
        Text(
            text = date.day, // day "Mon", "Tue"
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.outline
        )
        Card(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp)
                .clickable { onClickListener(date) },
            colors = CardDefaults.cardColors(
                containerColor = if (date.isSelected) {
                    MaterialTheme.colorScheme.tertiary
                } else {
                    MaterialTheme.colorScheme.surface
                }
            ),
        ) {
            Column(
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = date.date.toFormattedDateShortString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = if (date.isSelected) {
                        FontWeight.Medium
                    } else {
                        FontWeight.Normal
                    }
                )
            }
        }
    }
}

@Composable
fun DateHeader(
    data: CalendarModel,
    onPrevClickListener: (Date) -> Unit,
    onNextClickListener: (Date) -> Unit
) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = if (data.selectedDate.isToday) {
                "Today"
            } else {
                data.selectedDate.date.toFormattedMonthDateString()
            },
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary
        )
        IconButton(onClick = {
            onPrevClickListener(data.startDate.date)
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = "Back"
            )
        }
        IconButton(onClick = {
            onNextClickListener(data.endDate.date)
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = "Next"
            )
        }
    }
}


