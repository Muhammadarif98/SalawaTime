package com.example.salawatime.data.reminder

import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder")
data class ReminderDatabase (
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "description")  val description : String,
    @ColumnInfo(name = "isSwitchOn") var isSwitchOn: Boolean
    )

val reminderDataList = listOf(
    ReminderDatabase(
        id = 1,
        title = "Салават",
        description = "Напомнить за полчаса",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 2,
        title = "Хатму",
        description = "Напомнить за полчаса",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 3,
        title = "Салават",
        description = "Напомнить за час",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 4,
        title = "Хатму",
        description = "Напомнить за час",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 5,
        title = "Салават",
        description = "Напомнить за 2 часа",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 6,
        title = "Хатму",
        description = "Напомнить за 2 часа",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 7,
        title = "Салават",
        description = "Напомнить за 3 часа",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 8,
        title = "Хатму",
        description = "Напомнить за 3 часа",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 9,
        title = "Салават",
        description = "Напомнить за 6 часов",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 10,
        title = "Хатму",
        description = "Напомнить за 6 часов",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 11,
        title = "Салават",
        description = "Напомнить в полдень",
        isSwitchOn = false
    ),
    ReminderDatabase(
        id = 12,
        title = "Хатму",
        description = "Напомнить в полдень",
        isSwitchOn = false
    ),
)
