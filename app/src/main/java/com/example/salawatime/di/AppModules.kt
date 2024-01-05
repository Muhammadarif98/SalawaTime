package com.example.salawatime.di

import android.app.Application
import androidx.room.Room
import com.example.salawatime.data.todo.database.TodoDBApp
import com.example.salawatime.data.todo.database.TodoDaoApp
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
    ) : TodoDBApp =
        Room.databaseBuilder(
            application,
            TodoDBApp::class.java,
            "My_Db").build()

    @Singleton
    @Provides
    fun getDao(dbApp: TodoDBApp) : TodoDaoApp = dbApp.getDaoApp()

}