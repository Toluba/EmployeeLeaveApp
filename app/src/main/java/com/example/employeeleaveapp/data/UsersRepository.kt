package com.example.employeeleaveapp.data

class UsersRepository(private val userDao: UserDao): UserRepo {
    override suspend fun addUser(user: User){
        userDao.addUser(user)
    }

   override suspend fun getUser(email:String, password: String): User?{
        return userDao.getUser(email, password)
    }
}

interface UserRepo {

    suspend fun addUser(user: User)
    suspend fun getUser(email: String, password: String): User?
}

