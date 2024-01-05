package com.example.salawatime.presentation.ui.screen.reminder.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.salawatime.data.reminder.ReminderProvider
import java.text.SimpleDateFormat
import java.util.*

fun sendNotification(
    context: Context,
    titleKey: String,
    descriptionKey: String,
    delayInSeconds: Int,
    location: String,
    sharedPreferences: SharedPreferences
) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, NotificationReceiver::class.java).apply {
        putExtra("titleKey", titleKey)
        putExtra("descriptionKey", descriptionKey)
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        titleKey.hashCode(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    val sdf = SimpleDateFormat("d MMMM", Locale("ru"))
    val today = sdf.format(Date())
    val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    var timeForToday: String? = null

    if (location == "Хунзах") {
        if (dayOfWeek == Calendar.THURSDAY) {
            timeForToday = ReminderProvider.datesKhunzakhSalawat[today]
        } else if (dayOfWeek == Calendar.SUNDAY) {
            timeForToday = ReminderProvider.datesKhunzakhHatmu[today]
        }
        sharedPreferences.edit().putString("location", "Хунзах").apply()
    } else if (location == "Махачкала") {
        if (dayOfWeek == Calendar.THURSDAY) {
            timeForToday = ReminderProvider.datesMakhachkalaSalawat[today]
        } else if (dayOfWeek == Calendar.SUNDAY) {
            timeForToday = ReminderProvider.datesMakhachkalaHatmu[today]
        }
        sharedPreferences.edit().putString("location", "Махачкала").apply()
    }


    if (timeForToday != null) {
        val calendar = Calendar.getInstance()
        val hour = timeForToday.split(":")[0].toInt()
        val minute = timeForToday.split(":")[1].toInt()

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        // Если текущее время уже прошло указанное время, добавляем 7 дней
        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 7)
        }

        // Уведомление за delayInSeconds минут до заданного времени
        calendar.add(Calendar.MINUTE, -delayInSeconds)

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent)
    }
}
