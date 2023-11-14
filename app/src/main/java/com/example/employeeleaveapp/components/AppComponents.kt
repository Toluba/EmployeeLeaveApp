package com.example.employeeleaveapp.components




import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button

import androidx.compose.material3.DatePickerDefaults

import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldComponent(labelValue: String, icon: ImageVector) {

    var textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        value = textValue.value,
        onValueChange = { textValue.value = it },
        label = { Text(labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.tertiary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        leadingIcon = { Icon(imageVector = icon, contentDescription = "") },
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(labelValue: String, icon: ImageVector) {

    val password = remember {
        mutableStateOf("")
    }

    var passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text(labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.tertiary,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = { Icon(imageVector = icon, contentDescription = "") },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }

            var description = if (passwordVisible.value) {
                "Hide password"
            } else {
                "Show password"
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun ButtonComponent(value: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        shape = RoundedCornerShape(50.dp),
        contentPadding = PaddingValues(),
    ) {
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}