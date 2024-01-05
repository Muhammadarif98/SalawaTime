package com.example.salawatime.presentation.ui.screen.todo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.salawatime.data.todo.Task

import kotlinx.coroutines.launch
import com.example.salawatime.R
import com.example.salawatime.presentation.ui.screen.todo.ViewModel.MainViewModel
import com.example.salawatime.presentation.ui.screen.todo.ui.ButtonAddTask
import com.example.salawatime.presentation.ui.screen.todo.ui.EmptyState
import com.example.salawatime.presentation.ui.screen.todo.ui.TodoActionBar
import com.example.salawatime.presentation.ui.screen.todo.ui.TodoCardUI
import com.example.salawatime.presentation.ui.screen.todo.ui.TodoUI
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun ToDoScreen(viewModel: MainViewModel) {
    Scaffold(
    ) {
        TodoContent(viewModel)
    }
}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TodoContent(viewModel: MainViewModel) {
    val list: State<List<Task>> = viewModel.listTasks.collectAsState(listOf())
    Column(
        modifier = Modifier
            .paint(
                painterResource(id = R.drawable.todoback),
                contentScale = ContentScale.FillBounds
            )
            .padding(bottom = 84.dp)
    )
    {
        TodoActionBar()
        TodoUI(viewModel = viewModel)
    }
    // Show animation empty state
    if (list.value.isEmpty()) EmptyState()
    // Button Add Task
    ButtonAddTask()
}