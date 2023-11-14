package com.example.employeeleaveapp.requests

import android.content.Context
import androidx.compose.material3.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.employeeleaveapp.components.HeadingTextComponent
import com.example.employeeleaveapp.components.MyTextFieldComponent
import com.example.employeeleaveapp.ui.theme.Calendar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen() {
    Surface(modifier = Modifier.background(color = Color.Blue)) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            HeadingTextComponent(value = "Input Leave request")
            Spacer(modifier = Modifier.height(20.dp))
            LeaveTypeDropdown("Type of Leave")

            MyTextFieldComponent(labelValue = "Start Date", icon = Icons.Outlined.CalendarToday)

            MyTextFieldComponent(labelValue = "End Date", icon = Icons.Outlined.CalendarToday)

            var text by remember { mutableStateOf("") }

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Notes") }
            )
            ElevatedButton(onClick = { /*TODO*/ }) {
                Text("Submit")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaveTypeDropdown(labelValue: String) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var leaveType by remember {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { newValue -> isExpanded = newValue })
    {
        TextField(
            value = leaveType,
            onValueChange = {},
            label = { Text(labelValue) },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                Text(text = "Please select your leave type")
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            },

            ) {
            DropdownMenuItem(
                text = {
                    Text(text = "Annual Leave")
                },
                onClick = {
                    leaveType = "Annual Leave"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Sick Leave")
                },
                onClick = {
                    leaveType = "Sick Leave"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = "K Day")
                },
                onClick = {
                    leaveType = "K Day"
                    isExpanded = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaveDatePicker() {
    var startDate by remember {mutableStateOf(Date())}
    var endDate  by remember {mutableStateOf(Date())}
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier)
    val openDialog = remember { mutableStateOf(true) }
// TODO demo how to read the selected date from the state.
    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        snackScope.launch {
                            snackState.showSnackbar(
                                "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                            )
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }


}

    @Preview(showSystemUi = true)
    @Composable
    fun RequestPreview() {
        RequestScreen()
    }

    @Preview(showSystemUi = true)
    @Composable
    fun DatePickerPreview() {
        LeaveDatePicker()
    }