package com.example.salawatime.presentation.ui.screen.reminder.component

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.salawatime.R
import com.example.salawatime.presentation.ui.screen.reminder.component.ContentScreen
import com.example.salawatime.presentation.ui.screen.reminder.other.DropDownViewModel
import com.example.salawatime.presentation.ui.screen.reminder.other.DropDownViewModelFactory
import com.example.salawatime.presentation.ui.theme.DropdownSample
import com.example.salawatime.presentation.ui.theme.InfoDialog
import com.example.salawatime.presentation.worker.WorkViewModel

@SuppressLint("CoroutineCreationDuringComposition", "RememberReturnType")
@Composable
fun ReminderList( workViewModel : WorkViewModel) {
    val gr4 = Color(0xFF604D2E)

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val factory = DropDownViewModelFactory(sharedPreferences)
    val mainViewModel: DropDownViewModel = viewModel(factory = factory)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.back),
                contentScale = ContentScale.FillBounds
            )
            .padding(bottom = 84.dp)

    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 42.dp, bottom = 16.dp),
            text = "НАПОМИНАНИЕ",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = gr4,
            style = MaterialTheme.typography.bodyLarge
        )
        Column(
            Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                Modifier
                    .padding(start = 30.dp, end = 55.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,) {
                Row() {
                    DropdownSample(mainViewModel)
                }
                InfoDialog()
            }
        }
        ContentScreen(mainViewModel,workViewModel,sharedPreferences)
    }

}
