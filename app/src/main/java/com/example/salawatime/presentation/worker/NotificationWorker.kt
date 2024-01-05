package com.example.salawatime.presentation.worker

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.salawatime.R
import com.example.salawatime.data.reminder.SwitchInfo
import com.example.salawatime.presentation.ui.screen.reminder.notification.sendNotification
import java.util.Calendar

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {

        val sharedPreferences = applicationContext.getSharedPreferences("YourSharedPreferences", Context.MODE_PRIVATE)
        val switchInfo = listOf(
            SwitchInfo("switch1", "Салават", "15 мин до Салавата", 15),
            SwitchInfo("switch1", "Салават", "30 мин до Салавата", 30),
            SwitchInfo("switch2", "Салават", "1 час до Салавата", 60),
            SwitchInfo("switch3", "Салават", "2 часа до Салавата", 120),
            SwitchInfo("switch4", "Салават", "3 часа до Салавата", 180),
            SwitchInfo("switch5", "Салават", "6 часов до Салават", 360),
            SwitchInfo("switch6", "Хатму", "15 мин до Хатму", 15),
            SwitchInfo("switch6", "Хатму", "30 мин до Хатму", 30),
            SwitchInfo("switch7", "Хатму", "1 час до Хатму", 60),
            SwitchInfo("switch8", "Хатму", "2 часа до Хатму", 120),
            SwitchInfo("switch9", "Хатму", "3 часа до Хатму", 180),
            SwitchInfo("switch10", "Хатму", "6 часов до Хатму", 360)
        )
        val currentLocation = inputData.getString("currentLocation") ?: ""

        for (info in switchInfo) {
            val switchState = sharedPreferences.getBoolean(info.switchKey, false)
            if (switchState) {
                val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
                if (dayOfWeek == Calendar.THURSDAY && info.title == "Салават" || dayOfWeek == Calendar.SUNDAY && info.title == "Хатму") {
                    Log.d("NotificationWorker", "Sending notification for ${info.title}")
                    sendNotification(
                        applicationContext,
                        info.title,
                        info.description,
                        info.time,
                        currentLocation,
                        sharedPreferences
                    )
                    sharedPreferences.edit().putString("location", currentLocation).apply()
                }
            }
        }

        val builder = NotificationCompat.Builder(applicationContext, "notify")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("titleKey")
            .setContentText("Осталось descriptionKey не забывайте!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notify(200, builder.build())

            }
        }
        Log.d("NotificationWorker", "Work completed")
        return Result.success()
    }
}
