package com.example.salawatime.presentation.ui.screen.todo.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salawatime.data.todo.Task

@Composable
fun TodoCardUI(
    task: Task, isComplite: Boolean,
    onCompliteChange: (Boolean) -> Unit,
) {
    CardUI(
        task = task,
        isComplite = isComplite,
        onCompliteChange = onCompliteChange
    )
}

@Composable
fun CardUI(
    task: Task, isComplite: Boolean,
    onCompliteChange: (Boolean) -> Unit,
) {
    var visibleDescription by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFEFAE7)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.padding(10.dp),
                    checked = isComplite,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFFD8BB8B),
                        checkmarkColor = Color(0xFFFFF8D6),
                        uncheckedColor = Color(0xFFD8BB8B)
                    ),
                    onCheckedChange = onCompliteChange,
                )
                Text(
                    text = task.title,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = if (task.complitable) TextDecoration.LineThrough
                                         else TextDecoration.None),
                )
                IconButton(
                    onClick = { visibleDescription = !visibleDescription },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        imageVector = if (visibleDescription) Icons.Default.KeyboardArrowUp
                                      else Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color(0xFF827868)
                    )
                }
            }
            AnimatedVisibility(
                visible = visibleDescription,
            ) {
                Column(
                    modifier = Modifier
                        .background(Color(0xFFFEFAE7))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                            .background(Color(0xFFFEFAE7)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = task.description.toString(),
                            fontSize = 17.sp,
                            color = Color(0xFF827868),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                        )
                    }
                }
            }
        }
    }
}