package com.example.salawatime.presentation.ui.screen.reminder


import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.salawatime.R
import com.example.salawatime.data.reminder.ReminderDatabase
import com.example.salawatime.data.reminder.reminderDataList
import com.example.salawatime.data.todo.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun ReminderScreen(reminderViewModel: ReminderViewModel) {
    Scaffold(
    ) {
        ReminderList(reminderViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "RememberReturnType")
@Composable
fun ReminderList(reminderViewModel: ReminderViewModel) {
    val context = LocalContext.current
    val gr4 = Color(0xFF604D2E)
    val stateScroll = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

   // val reminderData: List<ReminderDatabase> = rememberSaveable() { ReminderDataProvider.reminderDataList}
    val list: State<List<ReminderDatabase>>  = reminderViewModel.listReminders.collectAsState(
        initial = reminderDataList
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.back),
                contentScale = ContentScale.FillBounds
            )
            .padding(bottom = 84.dp)

    ) {
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 42.dp, bottom = 16.dp),
            text = "НАПОМИНАНИЕ",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = gr4,
            style = MaterialTheme.typography.bodyLarge
        )
/*
//        Button(
//            onClick = {
//                val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//                val intent = Intent(context, NotificationReceiver::class.java)
//                val pendingIntent = PendingIntent.getBroadcast(
//                    context,
//                    0,
//                    intent,
//                    PendingIntent.FLAG_MUTABLE
//                )
//                val calendar = Calendar.getInstance()
//                calendar.timeInMillis = System.currentTimeMillis()
//                calendar.add(Calendar.SECOND, 5)
//                alarmManager.set(
//                    AlarmManager.RTC_WAKEUP,
//                    calendar.timeInMillis,
//                    pendingIntent
//                )
//            }) {
//            Text("Напоминание")
//        }
*/
        LazyColumn(
            state = stateScroll, modifier = Modifier.background(color = Color.Transparent),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            coroutineScope.launch {
                stateScroll.animateScrollToItem(0, 0)
            }
            items(items = reminderDataList, key = { reminder -> reminder.id },
                itemContent = { reminder ->

                    var complete by remember { mutableStateOf(reminder.isSwitchOn) }
                    ReminderListItem(
                        reminderDatabase = reminder,
                        isChecked = complete,
                        onSwitchCheckedChange = {
                            complete = it
                            reminder.isSwitchOn = !reminder.isSwitchOn
                            reminderViewModel.updateReminder(reminder)
                           // reminderViewModel.updateSwitchState(reminder.id,complete)
                        }
                    )
                }

            )
        }
    }


    // val reminderData = rememberSaveable() { ReminderDataProvider.reminderDataList}
    // val complite by remember { mutableStateOf(true) }


}

