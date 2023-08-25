package com.example.salawatime.presentation.ui.screen.todo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.salawatime.data.todo.Task

import kotlinx.coroutines.launch
import com.example.salawatime.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun ToDoScreen(viewModel: MainViewModel) {
    Scaffold(
    ) {
        Greeting(viewModel)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(viewModel: MainViewModel) {
    var textSearch by remember { mutableStateOf("") }
    val stateScroll = rememberLazyListState()
    val context = LocalContext.current
    val list: State<List<Task>> = viewModel.listTasks.collectAsState(listOf())
    val coroutineScope = rememberCoroutineScope()

    // Action Bar
    Column(modifier = Modifier
        .paint(
        painterResource(id = R.drawable.todoback),
        contentScale = ContentScale.FillBounds)
        .padding(bottom = 84.dp)) {

        // Title
        Text(
            text = "Список Дел",
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            fontStyle = FontStyle.Italic
        )

        // Text field Search
        TextField(
            value = textSearch,
            onValueChange = { textSearch = it },
            label = {
                Text(text = stringResource(id = R.string.search_title))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 30.dp),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor =  Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent ,
                containerColor = ( Color(0xFFFEFAE7)),
                textColor = Color.Black
            )
        )

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
                        }
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
                            }
                        )
            }
        }
    }


    // Show animation empty state
    if (list.value.isEmpty()) EmptyState()


    // Button Add Task
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)


    ) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .size(150.dp, 55.dp),

                //.background( Color(0xFFD8BB8B)),
            text = { Text(text = "Добавить") },
            onClick = {
                context.startActivity(Intent(context, AddTaskActivity::class.java))
            },
            containerColor = ( Color(0xFFD8BB8B)),
            icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "") },
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        )
    }
}

@Composable
fun EmptyState() {
    val animation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_empty))

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
            color = Color.Black
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ItemTask(task: Task, isComplite: Boolean, onCompliteChange: (Boolean) -> Unit) {

    var visibleDescription by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
        shape = RoundedCornerShape(15.dp)
    ) {

        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background( Color(0xFFFEFAE7)),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Checkbox(
                    modifier = Modifier.padding(10.dp),
                    checked = isComplite ,
                    colors  = CheckboxDefaults.colors(checkedColor = Color(0xFFD8BB8B),
                        checkmarkColor = Color(0xFFFFF8D6),
                        uncheckedColor = Color(0xFFD8BB8B)),
                    onCheckedChange = onCompliteChange ,
                )

                Text(
                    text = task.title,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textDecoration = if (task.complitable) TextDecoration.LineThrough else TextDecoration.None)
                )

                IconButton(
                    onClick = { visibleDescription = !visibleDescription },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        imageVector = if (visibleDescription) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color(0xFF827868)
                    )
                }
            }

            AnimatedVisibility(visible = visibleDescription) {
                Text(
                    text = task.description.toString(),
                    fontSize = 17.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun View() {
    //  val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

}