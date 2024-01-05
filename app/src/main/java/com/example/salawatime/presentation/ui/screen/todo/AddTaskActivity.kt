package com.example.salawatime.presentation.ui.screen.todo

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.salawatime.R
import com.example.salawatime.data.todo.Task
import com.example.salawatime.presentation.ui.screen.todo.ViewModel.AddTaskViewModel
import com.example.salawatime.presentation.ui.theme.SalawaTimeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalawaTimeTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = false
                    )
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = ViewModelProvider(this)[AddTaskViewModel::class.java]
                    TaskScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun TaskScreen(viewModel: AddTaskViewModel) {
    Column(
        modifier = Modifier
            .paint(
                painterResource(id = R.drawable.addtaskback),
                contentScale = ContentScale.FillBounds
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TaskUI(viewModel = viewModel)
    }
}

@Composable
fun TaskUI(viewModel: AddTaskViewModel){
    val activity = (LocalContext.current as? Activity)
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        // Edite Text Title
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 40.dp, end = 40.dp),
            value = title,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF000000)),
            onValueChange = { newTitle -> title = newTitle },
            label = {
                Text(text = "Заглавие")
            },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White.copy(
                    alpha = 0.5f
                )
            )

        )
        // Edite Text Description
        OutlinedTextField(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(top = 30.dp, start = 40.dp, end = 40.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF000000)),
            value = description,
            onValueChange = { description = it },
            label = {
                Text(text = "Описание")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White.copy(
                    alpha = 0.5f
                )
            )
        )

    }
    Column(
        Modifier.padding(bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        // Добавление новой задачи
        Button(
            onClick = {
                if (viewModel.validateFields()) {

                    val task = Task(null, title, description, false)
                    val updatedTask =
                        Task(task.id, "newTitle", "newDescription", task.complitable)
                    viewModel.updateTask(updatedTask)
                    // viewModel.addTask(task.id)
                    activity?.finish()
                } else {
                    if (title.isEmpty()) Toast.makeText(
                        activity?.baseContext,
                        "Заглавие пусто", Toast.LENGTH_SHORT
                    ).show()
                    else {

                        val task = Task(null, title, description, false)
                        viewModel.addTask(task)
                        activity?.finish()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF827868)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 30.dp, start = 40.dp, end = 40.dp, bottom = 0.dp),
        ) {
            Text(
                style = MaterialTheme.typography.labelSmall,
                text = "Сохранить",
                fontSize = 16.sp,
            )
        }
        Text(
            text = "ВЫЙТИ",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(top = 100.dp)
                .clickable { activity?.finish() },
            color = Color(0xFF36332D),
            fontSize = 16.sp,
        )
    }
}