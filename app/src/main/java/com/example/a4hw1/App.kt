package com.example.a4hw1

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a4hw1.room.AppDataBase

class App: Application() {

    companion object{
        lateinit var appDataDataBase: AppDataBase
    }
    override fun onCreate() {
        super.onCreate()
        appDataDataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
}