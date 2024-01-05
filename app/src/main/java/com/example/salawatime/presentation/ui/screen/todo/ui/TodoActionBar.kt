package com.example.salawatime.presentation.ui.screen.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salawatime.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoActionBar() {
    var textSearch by remember { mutableStateOf("") }
    Column() {
        // Action Bar
        Text(
            text = "Список Дел",
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            fontStyle = FontStyle.Normal
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
                focusedTextColor = Color.Black
            )
        )
    }
}