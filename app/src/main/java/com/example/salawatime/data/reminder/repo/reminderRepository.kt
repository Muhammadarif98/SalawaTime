package com.example.salawatime.data.reminder.repo


import com.example.salawatime.data.reminder.ReminderDatabase
import com.example.salawatime.data.reminder.database.reminderDaoApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class reminderRepository @Inject constructor(private val reminderDaoApp: reminderDaoApp)  {

    suspend fun getReminders(reminderDatabase: ReminderDatabase) = reminderDaoApp.getReminder(reminderDatabase)

    fun getAllReminders() : Flow<List<ReminderDatabase>> = flow {
        reminderDaoApp.getAllRemindersDistinctUntilChanged().collect {
            emit(it)
        }
    }

    suspend fun updateSwitchState(id: Int, isSwitchOn: Boolean) {
        reminderDaoApp.updateSwitchState(id, isSwitchOn)
    }

    suspend fun updateReminder(reminderDatabase: ReminderDatabase) {
        reminderDaoApp.updateReminder(reminderDatabase)
    }
    suspend fun deleteReminder(reminderDatabase: ReminderDatabase) = reminderDaoApp.deleteReminder(reminderDatabase)

}