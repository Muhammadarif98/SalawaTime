package com.example.salawatime.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(text = "Home Screen", fontSize = 50.sp)
    }

}