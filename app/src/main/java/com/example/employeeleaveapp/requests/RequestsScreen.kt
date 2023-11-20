package com.example.employeeleaveapp.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.employeeleaveapp.components.HeadingTextComponent
import com.example.employeeleaveapp.components.LeaveTypeDropdown
import com.example.employeeleaveapp.extension.toFormattedDateString
import com.example.employeeleaveapp.login.UserLeaveViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

//TODO - fix date title
//Todo - display mode
//TODO - change label from start date to choose a date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen(
    userLeaveViewModel: UserLeaveViewModel = viewModel(factory = UserLeaveViewModel.Factory),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarVisible = userLeaveViewModel.snackbarVisible.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = System.currentTimeMillis(),
        initialSelectedEndDateMillis = null,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= getCurrentDayMillis()
            }
        }
    )


    var startDateMillis by rememberSaveable {
        mutableStateOf(
            dateRangePickerState.selectedStartDateMillis
        )
    }
    var endDateMillis by rememberSaveable {
        mutableStateOf(
            dateRangePickerState.selectedEndDateMillis
        )
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Surface(
            modifier = Modifier
                .background(color = Color.Blue)
                .padding(it)
        ) {

            if (snackbarVisible.value) {
                LaunchedEffect(key1 = Unit, block = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Leave Request submitted")
                    }
                })
            }

            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                HeadingTextComponent(value = "Input Leave request")
                Spacer(modifier = Modifier.height(30.dp))

                ElevatedCard {
                    LeaveTypeDropdown("Type of Leave")
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        value = "${startDateMillis?.toFormattedDateString() ?: ""} - ${endDateMillis?.toFormattedDateString() ?: ""}",
                        onValueChange = {},
                        trailingIcon = {
                            Icon(
                                Icons.Outlined.DateRange,
                                "",
                                Modifier.clickable { showBottomSheet = true })
                        },
                        //interactionSource = interactionSource,
                        label = { Text("Start Date - End Date") }
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    ElevatedButton(onClick = {
                        userLeaveViewModel.onRequestSubmit(
                            startDateMillis,
                            endDateMillis
                        )
                    }

                    ) {
                        Text("Submit")
                    }

                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                showBottomSheet = false
                            },
                            sheetState = sheetState,
                            content = {
                                LeaveDateRangePicker(
                                    state = dateRangePickerState,
                                    onConfirmClicked = { selectedStartDateInMillis, selectedEndDateInMillis ->
                                        startDateMillis = selectedStartDateInMillis
                                        endDateMillis = selectedEndDateInMillis
                                        showBottomSheet = false
                                    },
                                )
                            },
                            scrimColor = Color.Black.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        )
                    }
                }
            }
        }


    }
}

private fun getCurrentDayMillis(): Long {
    val today = Calendar.getInstance()
    today.set(Calendar.HOUR_OF_DAY, 0)
    today.set(Calendar.MINUTE, 0)
    today.set(Calendar.SECOND, 0)
    today.set(Calendar.MILLISECOND, 0)
    return today.timeInMillis
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaveDateRangePicker(
    state: DateRangePickerState,
    onConfirmClicked: (selectedStartDateMillis: Long, selectedEndDateMillis: Long) -> Unit,
) {
    DateRangePicker(
        state,
        modifier = Modifier,
        // dateFormatter = DatePickerFormatter(),
        title = {
            Text(
                text = "Select date range to assign the chart", modifier = Modifier
                    .padding(16.dp)
            )
        },
        headline = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Box(Modifier.weight(0.2f)) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Okk",
                        modifier = Modifier.clickable {
                            if (state.selectedStartDateMillis != null && state.selectedEndDateMillis != null) {
                                onConfirmClicked(
                                    state.selectedStartDateMillis!!,
                                    state.selectedEndDateMillis!!
                                )
                            }
                        })
                }

            }
        },

        showModeToggle = true
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDateSelectionField() {

}

@Preview(showSystemUi = true)
@Composable
fun RequestPreview() {
    RequestScreen()
}

