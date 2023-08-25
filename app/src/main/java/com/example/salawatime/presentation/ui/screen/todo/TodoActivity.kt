package com.example.salawatime.presentation
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.Card
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ExtendedFloatingActionButton
//import androidx.compose.material3.FloatingActionButtonDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Switch
//import androidx.compose.material3.SwitchDefaults
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextDecoration
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.ViewModelProvider
//import com.airbnb.lottie.compose.LottieAnimation
//import com.airbnb.lottie.compose.LottieCompositionSpec
//import com.airbnb.lottie.compose.rememberLottieComposition
//import com.example.salawatime.R
//import com.example.salawatime.data.todo.Task
//import com.example.salawatime.presentation.ui.screen.todo.AddTaskActivity
//import com.example.salawatime.presentation.ui.theme.SalawaTimeTheme
//import dagger.hilt.android.AndroidEntryPoint
//
//
//import kotlinx.coroutines.launch
//
//
//
//@AndroidEntryPoint
//class TodoActivity : ComponentActivity() {
//    @OptIn(ExperimentalMaterial3Api::class)
//    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            SalawaTimeTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background,
//
//                    ) {
//                    Scaffold() {
//                        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//                        Greeting(viewModel)
//                    }
//
//                }
//            }
//        }
//    }
//}
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//@Preview
//fun View (){
//    TodoActivity()
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("CoroutineCreationDuringComposition")
//@Composable
//fun Greeting(viewModel : MainViewModel) {
//    var textSearch by remember { mutableStateOf("") }
//    val stateScroll = rememberLazyListState()
//    val context = LocalContext.current
//    val list : State<List<Task>> = viewModel.listTasks.collectAsState(listOf())
//    val coroutineScope = rememberCoroutineScope()
//
//    // Action Bar
//    Column {
//
//        // Title
//        Text(
//            text = "ToDo List",
//            fontSize = 30.sp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 20.dp) ,
//            textAlign = TextAlign.Center ,
//            color = MaterialTheme.colorScheme.onBackground ,
//            fontStyle = FontStyle.Italic
//        )
//
//        // Text field Search
//        TextField(
//            value = textSearch,
//            onValueChange = { textSearch = it } ,
//            label = {
//                Text(text = stringResource(id = R.string.search_title))
//            } ,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 30.dp),
//            singleLine = true ,
//            shape = RoundedCornerShape(20.dp) ,
//            leadingIcon = {
//                Icon(imageVector = Icons.Default.Search , contentDescription = null)
//            } ,
//            colors = TextFieldDefaults.textFieldColors(
//                focusedIndicatorColor =  Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent ,
//                textColor = Color.Black
//            )
//        )
//
//
//        LazyColumn(state = stateScroll){
//            coroutineScope.launch {
//                stateScroll.animateScrollToItem(0,0)
//            }
//            items(list.value , key = {task -> task.id!! }){ task ->
//                var complite by remember { mutableStateOf(task.complitable) }
//                if (textSearch.isEmpty())
//                    ItemTask(
//                        task = task ,
//                        isComplite = complite,
//                        onCompliteChange = {
//                            complite = it
//                            task.complitable = !task.complitable
//                            viewModel.updateTask(task)
//                        }
//                    )
//                else
//                    if (task.title.lowercase().contains(textSearch))
//                        ItemTask(
//                            task = task ,
//                            isComplite = complite,
//                            onCompliteChange = {
//                                complite = it
//                                task.complitable = !task.complitable
//                                viewModel.updateTask(task)
//                            }
//                        )
//            }
//        }
//    }
//
//
//    // Show animation empty state
//    if (list.value.isEmpty()) EmptyState()
//
//
//    // Button Add Task
//    Column(
//        verticalArrangement = Arrangement.Bottom,
//        horizontalAlignment = Alignment.CenterHorizontally ,
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(10.dp)
//    ) {
//        ExtendedFloatingActionButton(
//            modifier = Modifier
//                .size(150.dp, 55.dp) ,
//            text = { Text(text = "ADD TASK") },
//            onClick = {
//                context.startActivity(Intent(context , AddTaskActivity::class.java))
//            } ,
//            icon = { Icon(imageVector = Icons.Default.Add , contentDescription = "")} ,
//            elevation = FloatingActionButtonDefaults.elevation(8.dp)
//        )
//    }
//}
//
//@Composable
//fun EmptyState(){
//    val animation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_empty))
//
//    Column(
//        modifier = Modifier.fillMaxSize() ,
//        verticalArrangement = Arrangement.Center ,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        LottieAnimation(
//            composition = animation ,
//            isPlaying = true ,
//            restartOnPlay = true ,
//            modifier = Modifier.fillMaxSize(fraction = 0.3f) ,
//            alignment = Alignment.Center
//        )
//        Text(
//            text = stringResource(id = R.string.no_records) ,
//            fontSize = 23.sp ,
//            fontWeight = FontWeight.Bold ,
//            color = Color.Black
//        )
//    }
//}
//
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun ItemTask(task: Task , isComplite : Boolean , onCompliteChange : (Boolean) -> Unit){
//
//    var visibleDescription by remember {
//        mutableStateOf(false)
//    }
//
//    Card(modifier = Modifier
//        .fillMaxWidth()
//        .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp) ,
//        shape = RoundedCornerShape(15.dp)
//    ) {
//
//        Column{
//            Row(
//                modifier = Modifier.fillMaxWidth() ,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//
//                Switch(
//                    modifier = Modifier.padding(10.dp),
//                    checked = isComplite ,
//                    colors = SwitchDefaults.colors(
//                        checkedThumbColor = Color(0xFFFFF8D6),
//                        checkedTrackColor = Color(0xFFBEA787),
//                        uncheckedThumbColor = Color(0xFFBEA787),
//                        uncheckedTrackColor = Color(0xFFFFF8D6),
//                    ),
//                    onCheckedChange = onCompliteChange ,
//
//                    )
//
//                Text(
//                    text = task.title ,
//                    fontSize = 17.sp ,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f),
//                    fontWeight = FontWeight.Bold ,
//                    style = TextStyle(textDecoration = if (task.complitable) TextDecoration.LineThrough else TextDecoration.None)
//                )
//
//                IconButton(
//                    onClick = { visibleDescription = !visibleDescription } ,
//                    modifier = Modifier.size(60.dp)
//                ) {
//                    Icon(
//                        imageVector = if (visibleDescription) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
//                        contentDescription = "" ,
//                        tint = Color.Black
//                    )
//                }
//            }
//
//            AnimatedVisibility(visible = visibleDescription) {
//                Text(
//                    text = task.description.toString(),
//                    fontSize = 17.sp ,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 10.dp, end = 10.dp)
//                )
//            }
//        }
//    }
//}