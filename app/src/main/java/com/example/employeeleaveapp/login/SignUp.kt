package com.example.employeeleaveapp.login


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.employeeleaveapp.components.ButtonComponent
import com.example.employeeleaveapp.components.HeadingTextComponent
import com.example.employeeleaveapp.components.MyTextFieldComponent
import com.example.employeeleaveapp.components.NormalTextComponent
import com.example.employeeleaveapp.components.PasswordTextFieldComponent


@Composable
fun SignUpScreen(
    userLeaveViewModel: UserLeaveViewModel,
    modifier: Modifier = Modifier
){
    Surface(
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .padding(28.dp)
    ){
        Column(modifier = modifier.fillMaxSize()){
            NormalTextComponent("Hey there,")
            HeadingTextComponent(value = "Create an Account")
            Spacer(modifier.height(20.dp))
            MyTextFieldComponent(labelValue = "First Name", Icons.Outlined.Person)
            MyTextFieldComponent(labelValue = "Last Name", Icons.Outlined.Person)
            MyTextFieldComponent(labelValue = "Email", Icons.Outlined.Email)
            PasswordTextFieldComponent(labelValue = "Password", Icons.Outlined.Lock)
            Spacer(modifier.height(30.dp))
            ButtonComponent(value = "Register",onClick ={} /* { NavController.navigate("login")}*/)

        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview(){
    //SignUpScreen()
}