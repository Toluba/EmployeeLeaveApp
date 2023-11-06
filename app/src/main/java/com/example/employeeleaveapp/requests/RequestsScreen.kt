package com.example.employeeleaveapp.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.employeeleaveapp.components.HeadingTextComponent

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

            Text(
                text = "Start Date",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "End Date",
                style = MaterialTheme.typography.bodyMedium
            )

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
            label = {Text(labelValue)},
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


@Composable
fun LeaveDatePicker() {

}

@Preview(showSystemUi = true)
@Composable
fun RequestPreview() {
    RequestScreen()
}