package com.example.salawatime.ui.screen.todo

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun ToDoScreen() {
//    val context = LocalContext.current
    val todoItems = remember { mutableStateListOf<String>() }
    val (text, setText) = remember { mutableStateOf("") }

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom =60.dp,end = 30.dp),

                onClick = {
                    if (text.isNotEmpty()) {
                        todoItems.add(text)
                        setText("")
                    } else {
                            Toast.LENGTH_SHORT
                    }
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        },
        content = {
            TodoList(
                todoItems = todoItems,
                onItemRemove = { index -> todoItems.removeAt(index) }
            )
        }
    )
        TextField(
            value = text,
            onValueChange = setText,
            label = { Text(text = "Список дел") },
            modifier = Modifier.padding(16.dp,top = 60.dp)
        )
    }


@ExperimentalFoundationApi
@Composable
fun TodoList(todoItems: List<String>, onItemRemove: (Int) -> Unit) {
    LazyColumn {
        itemsIndexed(todoItems) { index, todo ->
            TodoItem(todo = todo, onRemove = { onItemRemove(index) })
        }
    }
}

@Composable
fun TodoItem(todo: String, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = todo)
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = onRemove) {
                Icon(Icons.Filled.Delete, contentDescription = null)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun TodoAppPreview() {
    ToDoScreen()
}





