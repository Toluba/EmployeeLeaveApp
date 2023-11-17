package com.example.employeeleaveapp.data

class UsersRepository(private val userLeaveDao: UserLeaveDao): UserRepo {
    override suspend fun addUser(user: User){
        userLeaveDao.addUser(user)
    }

   override suspend fun getUser(email:String, password: String): User?{
        return userLeaveDao.getUser(email, password)
    }
}

interface UserRepo {

    suspend fun addUser(user: User)
    suspend fun getUser(email: String, password: String): User?
}

