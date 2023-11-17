package com.example.employeeleaveapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [User::class, Leave::class],  version = 1)
@TypeConverters(Converters::class)
abstract class UsersLeaveDatabase : RoomDatabase() {

    abstract fun userDao(): UserLeaveDao

    companion object {
        @Volatile
        private var Instance: UsersLeaveDatabase? = null

        fun getDatabase(context: Context): UsersLeaveDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UsersLeaveDatabase::class.java, "users_database")
                    .build().also { Instance = it }
            }
        }
    }
}