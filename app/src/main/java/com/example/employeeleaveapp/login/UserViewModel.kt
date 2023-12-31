package com.example.employeeleaveapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.employeeleaveapp.EmployeeLeaveApplication
import com.example.employeeleaveapp.data.User
import com.example.employeeleaveapp.data.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val usersRepository: UserRepo) : ViewModel(){
    private val _loggedIn = MutableStateFlow(false)
    val loggedIn: StateFlow<Boolean>
        get() = _loggedIn

    suspend fun signupUser (user: User){
        usersRepository.addUser(user)
    }

    private suspend fun loginUser(email: String, password: String) {
        val user = usersRepository.getUser(email, password)
        //user?.let { _loggedIn.value = true }
       _loggedIn.value = true
        //TODO - don't forget about this
    }

    fun onLoginClick(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            loginUser(email, password)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as EmployeeLeaveApplication).container.userRepo
                UserViewModel(
                    usersRepository = myRepository,
                )
            }
        }
    }

}