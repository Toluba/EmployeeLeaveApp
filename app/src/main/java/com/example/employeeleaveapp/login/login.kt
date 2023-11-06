package com.example.employeeleaveapp.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.employeeleaveapp.components.ButtonComponent
import com.example.employeeleaveapp.components.HeadingTextComponent
import com.example.employeeleaveapp.components.MyTextFieldComponent
import com.example.employeeleaveapp.components.PasswordTextFieldComponent

//TODO - add navigation into the onclicks etc
//TODO - if password incorrect, if email doesnt contain @

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .padding(28.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            HeadingTextComponent(value = "Login")
            Spacer(modifier = Modifier.height(60.dp))
            MyTextFieldComponent(labelValue = "Email", icon = Icons.Outlined.Mail)
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextFieldComponent(labelValue = "Password", icon = Icons.Outlined.Lock)
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(value = "Login")

            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(
                text = AnnotatedString("Forgot password?"),
                onClick = { },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default
                )
            )
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}