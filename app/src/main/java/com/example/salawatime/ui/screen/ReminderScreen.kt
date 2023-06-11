package com.example.salawatime.ui.screen

import android.provider.CalendarContract.Reminders
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ReminderScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(text = "Reminder Screen", fontSize = 50.sp)
    }

}