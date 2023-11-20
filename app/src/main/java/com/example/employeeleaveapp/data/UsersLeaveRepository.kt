package com.example.employeeleaveapp.data

import java.util.Date

class UsersRepository(private val userLeaveDao: UserLeaveDao): UserRepo {
    override suspend fun addUser(user: User){
        userLeaveDao.addUser(user)
    }

   override suspend fun getUser(email:String, password: String): User?{
        return userLeaveDao.getUser(email, password)
    }

    override suspend fun addLeave(leave: Leave) {
        userLeaveDao.addLeave(leave)
    }

    override suspend fun getLeave(startDate: Date, endDate: Date): List<Leave> {
        return userLeaveDao.getLeave(startDate, endDate)
    }
}

interface UserRepo {

    suspend fun addUser(user: User)
    suspend fun getUser(email: String, password: String): User?

    suspend fun addLeave(leave: Leave)

    suspend fun getLeave(startDate: Date, endDate: Date): List<Leave>
}

