package com.example.salawatime.data.reminder.database

import androidx.room.*
import com.example.salawatime.data.reminder.ReminderDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface reminderDaoApp {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun getReminder(reminderDatabase: ReminderDatabase)

    @Update
    suspend fun updateReminder(reminderDatabase: ReminderDatabase)

    @Delete
    suspend fun deleteReminder(reminderDatabase: ReminderDatabase)


    @Query("UPDATE reminder SET isSwitchOn = :isSwitchOn WHERE id = :id")
    suspend fun updateSwitchState(id: Int, isSwitchOn: Boolean)

    @Query("SELECT * FROM reminder ORDER BY id DESC")
    fun getAllReminders(): Flow<List<ReminderDatabase>>
    fun getAllRemindersDistinctUntilChanged() = getAllReminders().distinctUntilChanged()


}