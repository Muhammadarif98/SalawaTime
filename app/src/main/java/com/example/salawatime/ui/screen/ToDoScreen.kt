package com.example.salawatime.ui.screen

import android.annotation.SuppressLint
import android.graphics.Insets.add
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Composable
//fun ToDoScreen() {
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Gray)
//    ) {
//        Text(text = "ToDO Screen", fontSize = 50.sp)
//    }
//
//}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ToDoScreen() {
    var todoItems by remember { mutableStateOf(listOf<String>()) }
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("TODO List") }) },


        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Add TODO item") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(todoItems) { todo ->
//                        Text(
//                            text = todo,
//                            style = MaterialTheme(typography = Typography()) {
//
//                            },
//                            modifier = Modifier.padding(vertical = 4.dp)
//                        )
                    }
                }
            }
        }
    )
}

fun items(count: List<String>, itemContent: LazyItemScope.(index: Int) -> Unit) {

}
