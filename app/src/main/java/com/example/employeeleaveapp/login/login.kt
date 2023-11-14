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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.employeeleaveapp.components.ButtonComponent
import com.example.employeeleaveapp.components.HeadingTextComponent
import com.example.employeeleaveapp.components.MyTextFieldComponent
import com.example.employeeleaveapp.components.NormalTextComponent
import com.example.employeeleaveapp.components.PasswordTextFieldComponent

//TODO - add navigation into the onclicks etc


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory),
    navController: NavController,
    //modifier: Modifier = Modifier
) {
    val loggedIn by userViewModel.loggedIn.collectAsState()
    if (loggedIn){
        navController.navigate("login")
    }
    LoginScreenContent(userViewModel)
}

@Composable
private fun LoginScreenContent(userViewModel: UserViewModel) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NormalTextComponent(value = "Login")
            HeadingTextComponent(value = "Welcome Back")
            Spacer(modifier = Modifier.height(60.dp))
            MyTextFieldComponent(labelValue = "Email", icon = Icons.Outlined.Mail)
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextFieldComponent(labelValue = "Password", icon = Icons.Outlined.Lock)
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(
                value = "Login",
                onClick = { userViewModel.onLoginClick("tom@aol.com", "hello") }
            )

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
   // LoginScreen(userViewModel = UserViewModel())
}