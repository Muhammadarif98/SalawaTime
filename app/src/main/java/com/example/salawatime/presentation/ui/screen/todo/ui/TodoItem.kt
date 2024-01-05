package com.example.salawatime.presentation.ui.screen.todo.ui

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.salawatime.R
import com.example.salawatime.data.todo.Task
import com.example.salawatime.presentation.ui.screen.todo.AddTaskActivity
import com.example.salawatime.presentation.ui.screen.todo.ViewModel.MainViewModel
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemTask(
    task: Task, isComplite: Boolean,
    onCompliteChange: (Boolean) -> Unit,
    viewModel: MainViewModel
) {
    RevealSwipe(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
        shape = RoundedCornerShape(15.dp),
        directions = setOf(
            //RevealDirection.StartToEnd,
            RevealDirection.EndToStart
        ),
        onBackgroundEndClick = { viewModel.deleteTask(task) },
        backgroundCardEndColor = Color(0xFFD8BB8B),
        backgroundCardStartColor = Color(0xFF827868),
        hiddenContentStart = {
            Icon(
                modifier = Modifier.padding(horizontal = 28.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = stringResource(id = R.string.star),
                tint = Color.White
            )
        },
        hiddenContentEnd = {
            Icon(
                modifier = Modifier.padding(horizontal = 28.dp),
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(id = R.string.delete),
                tint = Color.White
            )
        }
    ) {
        TodoCardUI(
            task = task,
            isComplite = isComplite,
            onCompliteChange = onCompliteChange,
        )
    }
}
