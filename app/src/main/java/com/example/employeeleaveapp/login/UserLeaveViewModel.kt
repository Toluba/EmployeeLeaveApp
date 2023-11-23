package com.example.employeeleaveapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.employeeleaveapp.EmployeeLeaveApplication
import com.example.employeeleaveapp.calendar.model.Leave
import com.example.employeeleaveapp.data.LeaveEntity
import com.example.employeeleaveapp.data.TypeOfLeave
import com.example.employeeleaveapp.data.User
import com.example.employeeleaveapp.data.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class UserLeaveViewModel(private val usersRepository: UserRepo) : ViewModel() {
    private val _loggedIn = MutableStateFlow(false)
    val loggedIn: StateFlow<Boolean>
        get() = _loggedIn

    private val _snackbarVisible = MutableStateFlow(false)
    val snackbarVisible: StateFlow<Boolean>
        get() = _snackbarVisible

    private val _leaves = MutableStateFlow<List<Leave>>(emptyList())
    val leaves: StateFlow<List<Leave>>
        get() = _leaves
//    init {
//        loadLeaveRequest()
//    }

   fun signupUser(user: User) {
       viewModelScope.launch(Dispatchers.IO) {
           usersRepository.addUser(user)
       }
    }

    private suspend fun loginUser(email: String, password: String) {
        val user = usersRepository.getUser(email, password)
        //user?.let { _loggedIn.value = true }
        _loggedIn.value = true
        //TODO - don't forget about this
    }

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUser(email, password)
        }
    }

    fun onRequestSubmit(startTimestamp: Long?, endTimestamp: Long?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (startTimestamp != null && endTimestamp != null) {
                usersRepository.addLeave(
                    LeaveEntity(
                        email = "tom@gmail.com",
                        startDate = startTimestamp,
                        endDate = endTimestamp,
                        typeOfLeave = TypeOfLeave.Annual
                    )
                )
                _snackbarVisible.value = true
            }
        }
    }

//    private val _isLeaveRequestSaved = MutableSharedFlow<Unit>()
//    val isLeaveRequestSaved = _isLeaveRequestSaved.asSharedFlow()
//
//    fun addLeave( state: LeaveConfirmState) {
//        viewModelScope.launch {
//            val leave = state.leave
//            val leaveAdded = usersRepository.addLeave(leave)
//            _isLeaveRequestSaved.emit(leaveAdded)
//        }


    fun loadLeaveRequest(selectedDate: Date) {
        viewModelScope.launch() {

            usersRepository.getUserLeave(selectedDate).collect {
                _leaves.value = it
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[APPLICATION_KEY] as EmployeeLeaveApplication).container.userRepo
                UserLeaveViewModel(
                    usersRepository = myRepository,
                )
            }
        }
    }

}


data class LeaveConfirmState(
    val leave: List<Leave>
)
