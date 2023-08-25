package com.example.salawatime.di

import android.app.Application
import androidx.room.Room
import com.example.salawatime.data.reminder.database.reminderDBApp
import com.example.salawatime.data.reminder.database.reminderDaoApp
import com.example.salawatime.data.todo.database.todoDBApp
import com.example.salawatime.data.todo.database.todoDaoApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun getDbApp(
        application: Application
    ) : todoDBApp =
        Room.databaseBuilder(
            application,
            todoDBApp::class.java,
            "My_Db").build()

    @Singleton
    @Provides
    fun getDao(dbApp: todoDBApp) : todoDaoApp = dbApp.getDaoApp()

    @Singleton
    @Provides
    fun getReminderDbApp(
        application: Application
    ) : reminderDBApp =
        Room.databaseBuilder(
            application,
            reminderDBApp::class.java,
            "Reminder_Db").build()
    @Singleton
    @Provides
    fun getReminderDao(dbApp: reminderDBApp) : reminderDaoApp = dbApp.getReminderDaoApp()


}