package com.example.salawatime.presentation.ui.screen.todo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.salawatime.R
import com.example.salawatime.data.todo.Task
import com.example.salawatime.presentation.ui.screen.todo.ViewModel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun TodoUI (viewModel: MainViewModel){
    var textSearch by remember { mutableStateOf("") }
    val stateScroll = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val list: State<List<Task>> = viewModel.listTasks.collectAsState(listOf())
    LazyColumn(state = stateScroll) {
        coroutineScope.launch {
            stateScroll.animateScrollToItem(0, 0)
        }
        items(list.value, key = { task -> task.id!! }) { task ->
            var complite by remember { mutableStateOf(task.complitable) }
            if (textSearch.isEmpty())
                ItemTask(
                    task = task,
                    isComplite = complite,
                    onCompliteChange = {
                        complite = it
                        task.complitable = !task.complitable
                        viewModel.updateTask(task)
                    },
                    viewModel = viewModel,
                )
            else
                if (task.title.lowercase().contains(textSearch))
                    ItemTask(
                        task = task,
                        isComplite = complite,
                        onCompliteChange = {
                            complite = it
                            task.complitable = !task.complitable
                            viewModel.updateTask(task)
                        },
                        viewModel = viewModel,
                    )
        }
    }
}

@Composable
fun EmptyState() {
    val animation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lego))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = animation,
            isPlaying = true,
            restartOnPlay = true,
            modifier = Modifier.fillMaxSize(fraction = 0.3f),
            alignment = Alignment.Center
        )
        Text(
            text = stringResource(id = R.string.no_records),
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF604D2E)
        )
    }
}