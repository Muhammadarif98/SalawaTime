package com.example.salawatime.data.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.salawatime.data.todo.Task

@Database(entities = [Task::class] , version = 1 , exportSchema = false)
abstract class TodoDBApp : RoomDatabase() {
    abstract fun getDaoApp() : TodoDaoApp
}