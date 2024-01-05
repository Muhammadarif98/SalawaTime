package com.example.salawatime.presentation.ui.screen.todo.ui

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.salawatime.R
import com.example.salawatime.presentation.ui.screen.todo.AddTaskActivity

@Composable
fun ButtonAddTask() {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
    ) {
        var expanded by remember { mutableStateOf(false) }
        val favorite = stringResource(id = R.string.add)
        FloatingActionButton(
            onClick = {
                if (!expanded) {
                    expanded = !expanded
                } else {
                    context.startActivity(Intent(context, AddTaskActivity::class.java))
                }
            },
            backgroundColor = ( Color(0xFFD8BB8B)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
        ) {
            Row(
                Modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .background(Color(0xFFD8BB8B))) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = favorite,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                AnimatedVisibility(
                    expanded,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(text = "Добавить", style = MaterialTheme.typography.bodyLarge,)
                }
            }
        }
    }
}