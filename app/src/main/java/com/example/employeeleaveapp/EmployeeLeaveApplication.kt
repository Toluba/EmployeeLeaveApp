package com.example.employeeleaveapp

import android.app.Application
import com.example.employeeleaveapp.di.AppContainer
import com.example.employeeleaveapp.di.AppDataContainer

class EmployeeLeaveApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(applicationContext)
    }
}