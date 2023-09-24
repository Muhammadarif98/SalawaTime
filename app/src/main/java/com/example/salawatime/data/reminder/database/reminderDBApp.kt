package com.example.salawatime.data.reminder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.salawatime.data.reminder.ReminderDatabase


@Database(
    entities = [ReminderDatabase::class],
    version = 1,
    exportSchema = false)
abstract class reminderDBApp : RoomDatabase() {
    abstract fun getReminderDaoApp(): reminderDaoApp
}