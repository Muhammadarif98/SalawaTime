package com.example.salawatime.presentation.ui.screen.todo

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.salawatime.data.todo.Task
import com.example.salawatime.presentation.ui.theme.SalawaTimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalawaTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
                    Greeting2(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting2(viewModel : AddTaskViewModel) {

    val activity = (LocalContext.current as? Activity)

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize() , horizontalAlignment = Alignment.CenterHorizontally) {

        // Tool Bar
        Box() {
            Text(
                text = "Добавить Задачу",
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center ,
                color = MaterialTheme.colorScheme.onBackground ,
                fontWeight = FontWeight.Bold ,
            )

        }

        // Edite Text Title
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 40.dp, end = 40.dp),
            value = title,
            onValueChange = { title = it } ,
            label = {
                Text(text = "Заглавие")
            } ,
            singleLine = true
        )

        // Edite Text Description
        OutlinedTextField(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(top = 30.dp, start = 40.dp, end = 40.dp),
            value = description,
            onValueChange = { description = it } ,
            label = {
                Text(text = "Описание")
            }
        )

        // Button Save Task
        Button(
            onClick = {
                if (title.isEmpty()) Toast.makeText(activity?.baseContext , "Заглавие пусто" , Toast.LENGTH_SHORT).show()
                else {
                    val task = Task(null , title , description , false)
                    viewModel.addTask(task)
                    activity?.finish()
                }
            } ,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 50.dp, start = 40.dp, end = 40.dp),
        ) {
            Text(text = "Сохранить")
        }
        Button(
            onClick ={ activity?.finish() } ,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 8.dp, start = 130.dp, end = 130.dp),
        ) {
            Text(
                text = "Выйти")
        }

    }
}