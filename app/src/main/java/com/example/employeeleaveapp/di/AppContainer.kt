package com.example.employeeleaveapp.di

import android.content.Context
import com.example.employeeleaveapp.data.UserRepo
import com.example.employeeleaveapp.data.UsersDatabase
import com.example.employeeleaveapp.data.UsersRepository

interface AppContainer {
    val userRepo: UserRepo
}

class AppDataContainer(private val context: Context): AppContainer{
    override val userRepo: UserRepo by lazy {
        UsersRepository(UsersDatabase.getDatabase(context).userDao())
    }
}