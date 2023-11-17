package com.example.employeeleaveapp.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import com.example.employeeleaveapp.components.HeadingTextComponent
import com.example.employeeleaveapp.components.LeaveTypeDropdown
import com.waseefakhtar.doseapp.extension.toFormattedDateString
import java.util.Calendar

//TODO - fix date title
//Todo - display mode
//TODO - change label from start date to choose a date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen() {
    val state = rememberDateRangePickerState()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val today = Calendar.getInstance()
    today.set(Calendar.HOUR_OF_DAY, 0)
    today.set(Calendar.MINUTE, 0)
    today.set(Calendar.SECOND, 0)
    today.set(Calendar.MILLISECOND, 0)
    val currentDayMillis = today.timeInMillis
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = System.currentTimeMillis(),
        initialSelectedEndDateMillis = null,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= currentDayMillis
            }
        }
    )
    var startDate by rememberSaveable {
        mutableStateOf(
            dateRangePickerState.selectedStartDateMillis?.toFormattedDateString() ?: ""
        )
    }
    var endDate by rememberSaveable {
        mutableStateOf(
            dateRangePickerState.selectedEndDateMillis?.toFormattedDateString() ?: ""
        )
    }

    Surface(modifier = Modifier.background(color = Color.Blue)) {
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
                    value = "$startDate - $endDate",
                    onValueChange = {},
                    trailingIcon = { Icon(Icons.Outlined.DateRange, "", Modifier.clickable { showBottomSheet = true }) },
                    //interactionSource = interactionSource,
                    label = { Text("Start Date - End Date") }
                )

                Spacer(modifier = Modifier.height(10.dp))
                var text by remember { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Notes") }
                )


                Spacer(modifier = Modifier.height(30.dp))
                ElevatedButton(onClick = { /*TODO*/ }) {
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
                                    startDate = selectedStartDateInMillis.toFormattedDateString()
                                    endDate = selectedEndDateInMillis.toFormattedDateString()
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

@Composable
fun ScreenContent() {

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(/*endDate: (Long) -> Unit*/) {

    var shouldDisplay by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    if (isPressed) {
        shouldDisplay = true
    }

    val today = Calendar.getInstance()
    today.set(Calendar.HOUR_OF_DAY, 0)
    today.set(Calendar.MINUTE, 0)
    today.set(Calendar.SECOND, 0)
    today.set(Calendar.MILLISECOND, 0)
    val currentDayMillis = today.timeInMillis
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = System.currentTimeMillis(),
        initialSelectedEndDateMillis = null,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= currentDayMillis
            }
        }
    )


//TODO- fix date
    var startDate by rememberSaveable {
        mutableStateOf(
            dateRangePickerState.selectedStartDateMillis?.toFormattedDateString() ?: ""
        )
    }
    var endDate by rememberSaveable {
        mutableStateOf(
            dateRangePickerState.selectedEndDateMillis?.toFormattedDateString() ?: ""
        )
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        //readOnly = true,
        value = "$startDate - $endDate",
        onValueChange = {},
        trailingIcon = { Icon(Icons.Outlined.DateRange, "") },
        interactionSource = interactionSource,
        label = { Text("Start Date - End Date") }
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    state: DateRangePickerState,
    shouldDisplay: Boolean,
    onConfirmClicked: (selectedStartDateMillis: Long, selectedEndDateMillis: Long) -> Unit,
    dismissRequest: () -> Unit
) {
    if (shouldDisplay) {
        DatePickerDialog(
            onDismissRequest = dismissRequest,
            confirmButton = {
                Button(
                    modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp),
                    onClick = {
                        //                        state.selectedStartDateMillis?.let {
                        //                            onConfirmClicked(it)
                        //                        }}
                        if (state.selectedStartDateMillis != null && state.selectedEndDateMillis != null) {
                            onConfirmClicked(
                                state.selectedStartDateMillis!!,
                                state.selectedEndDateMillis!!
                            )
                        }
                        dismissRequest()
                    }
                ) {
                    Text(text = "ok")
                }
            },
            dismissButton = {
                TextButton(onClick = dismissRequest) {
                    Text(text = "cancel")
                }
            },
            content = {
                DateRangePicker(
                    state = state,
                    showModeToggle = false,
                    headline = {
                        //                        (if(state.selectedStartDateMillis!=null) state.selectedStartDateMillis?.toFormattedDateString() else "Start Date")?.let { Text(text = it) }
                        //
                        //                        (if(state.selectedEndDateMillis!=null) state.selectedEndDateMillis?.toFormattedDateString() else "End Date")?.let {Text(text = it)}

                        state.selectedEndDateMillis?.toFormattedDateString()?.let {
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = it
                            )
                        }
                    }
                )
            }
        )
    }
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

