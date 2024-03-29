package com.example.employeeleaveapp.data

import com.example.employeeleaveapp.calendar.model.Leave
import com.example.employeeleaveapp.data.mapper.toLeave
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class UsersRepository(private val userLeaveDao: UserLeaveDao) : UserRepo {
    override suspend fun addUser(user: User) {
        userLeaveDao.addUser(user)
    }

    override suspend fun getUser(email: String, password: String): User? {
        return userLeaveDao.getUser(email, password)
    }
    override suspend fun getUser(email: String): User? {
        return userLeaveDao.getUser(email)
    }


    override suspend fun addLeave(leave: LeaveEntity) {
        userLeaveDao.addLeave(leave)
    }


    override fun getUserLeave(selectedDate: Date): Flow<List<Leave>>{
        return userLeaveDao.getUserLeave(
            selectedDate = selectedDate
        ).map { listEntity  ->
            listEntity.map { it.toLeave() }
        }
    }
}


interface UserRepo {

    suspend fun addUser(user: User)
    suspend fun getUser(email: String, password: String): User?
    suspend fun getUser(email: String): User?
    fun getUserLeave(selectedDate: Date): Flow<List<Leave>>
    suspend fun addLeave(leave: LeaveEntity)
}

