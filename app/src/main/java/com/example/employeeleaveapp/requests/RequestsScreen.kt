package com.example.employeeleaveapp.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.employeeleaveapp.components.HeadingTextComponent
import com.example.employeeleaveapp.components.InputTextComponent
import com.example.employeeleaveapp.components.LeaveTypeDropdown
import com.waseefakhtar.doseapp.extension.toFormattedDateString
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen() {
    Surface(modifier = Modifier.background(color = Color.Blue)) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            HeadingTextComponent(value = "Input Leave request")
            Spacer(modifier = Modifier.height(30.dp))
            LeaveTypeDropdown("Type of Leave")
            Spacer(modifier = Modifier.height(10.dp))
            InputTextComponent(labelValue = "Start Date", icon = Icons.Outlined.CalendarToday)
            Spacer(modifier = Modifier.height(10.dp))
            InputTextComponent(labelValue = "End Date", icon = Icons.Outlined.CalendarToday)
            Spacer(modifier = Modifier.height(10.dp))
            var text by remember { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = { Text("Notes") }
            )

            DateTextField { it }
            Spacer(modifier = Modifier.height(30.dp))
            ElevatedButton(onClick = { /*TODO*/ }) {
                Text("Submit")
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(endDate: (Long) -> Unit) {

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
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= currentDayMillis
            }
        }
    )
//TODO- fix date
    var selectedDate by rememberSaveable {
        mutableStateOf(
            datePickerState.selectedDateMillis?.toFormattedDateString() ?: ""
        )
    }

    EndDatePickerDialog(
        state = datePickerState,
        shouldDisplay = shouldDisplay,
        onConfirmClicked = { selectedDateInMillis ->
            selectedDate = selectedDateInMillis.toFormattedDateString()
            endDate(selectedDateInMillis)
        },
        dismissRequest = {
            shouldDisplay = false
        }
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        value = selectedDate,
        onValueChange = {},
        trailingIcon = { Icons.Outlined.DateRange },
        interactionSource = interactionSource,
        label = {Text("End Date")}
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndDatePickerDialog(
    state: DatePickerState,
    shouldDisplay: Boolean,
    onConfirmClicked: (selectedDateInMillis: Long) -> Unit,
    dismissRequest: () -> Unit
) {
    if (shouldDisplay) {
        DatePickerDialog(
            onDismissRequest = dismissRequest,
            confirmButton = {
                Button(
                    modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp),
                    onClick = {
                        state.selectedDateMillis?.let {
                            onConfirmClicked(it)
                        }
                        dismissRequest()
                    }
                ) {
                    Text(text = "ok")
                }
            },
            dismissButton = {
                TextButton(onClick = dismissRequest) {
                    Text(text ="cancel")
                }
            },
            content = {
                DatePicker(
                    state = state,
                    showModeToggle = false,
                    headline = {
                        state.selectedDateMillis?.toFormattedDateString()?.let {
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


@Preview(showBackground = true)
@Composable
fun PreviewDateSelectionField() {

}

@Preview(showSystemUi = true)
@Composable
fun RequestPreview() {
    RequestScreen()
}

