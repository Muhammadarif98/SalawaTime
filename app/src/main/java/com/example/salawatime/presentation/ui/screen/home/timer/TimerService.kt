package com.example.salawatime.presentation.ui.screen.home.timer

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.coroutines.*

class TimerService : Service() {

    private var job: Job? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent?.getLongExtra("time", 0) ?: 0

        job = CoroutineScope(Dispatchers.Default).launch {
            for (i in time downTo 0) {
                delay(1000L)
                // Отправляем обновление пользовательскому интерфейсу
                val intent = Intent("TimerUpdate")
                intent.putExtra("timeLeft", i)
                LocalBroadcastManager.getInstance(this@TimerService).sendBroadcast(intent)
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
