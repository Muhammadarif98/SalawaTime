package com.example.salawatime.presentation.worker

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf


class WorkViewModel(private val application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("YourSharedPreferences", Context.MODE_PRIVATE)

    var currentLocation = mutableStateOf(
        sharedPreferences.getString("location", "Хунзах") ?: "Хунзах"
    )

    fun scheduleNotification() {
        val workData = workDataOf("currentLocation" to currentLocation.value)
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(workData)
            .build()
        WorkManager.getInstance(application).enqueue(workRequest)
    }
}


class MyViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
