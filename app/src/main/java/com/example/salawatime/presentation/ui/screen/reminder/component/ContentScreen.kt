package com.example.salawatime.presentation.ui.screen.reminder.component

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.salawatime.data.reminder.SwitchInfo
import com.example.salawatime.data.reminder.ReminderProvider
import com.example.salawatime.presentation.ui.screen.reminder.other.DropDownViewModel
import com.example.salawatime.presentation.ui.theme.AnimateContent
import com.example.salawatime.presentation.worker.WorkViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun ContentScreen(
    mainViewModel: DropDownViewModel,
    workViewModel: WorkViewModel,
    sharedPreferences: SharedPreferences) {
    val stateScroll = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val switchInfo = listOf(
        SwitchInfo("switch0", "Салават", "15 мин до Салавата", 15),
        SwitchInfo("switch1", "Салават", "30 мин до Салавата", 30),
        SwitchInfo("switch2", "Салават", "1 час до Салавата", 60),
        SwitchInfo("switch3", "Салават", "2 часа до Салавата", 120),
        SwitchInfo("switch4", "Салават", "3 часа до Салавата", 180),
        SwitchInfo("switch5", "Салават", "6 часов до Салават", 360),
        SwitchInfo("switch6", "Хатму", "15 мин до Хатму", 15),
        SwitchInfo("switch7", "Хатму", "30 мин до Хатму", 30),
        SwitchInfo("switch8", "Хатму", "1 час до Хатму", 60),
        SwitchInfo("switch9", "Хатму", "2 часа до Хатму", 120),
        SwitchInfo("switch10", "Хатму", "3 часа до Хатму", 180),
        SwitchInfo("switch11", "Хатму", "6 часов до Хатму", 360)
    )
    LazyColumn(
        state = stateScroll, modifier = Modifier.background(color = Color.Transparent),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        coroutineScope.launch {
            stateScroll.animateScrollToItem(0, 5)
        }
        item {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 65.dp, end = 65.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                ShowTime(mainViewModel)
            }
        }
        itemsIndexed(switchInfo) { _, info ->
            CardContent(info,mainViewModel,sharedPreferences)
        }
    }
}

@Composable
fun ShowTime(mainViewModel: DropDownViewModel) {
    val location = mainViewModel.currentLocation.value
    val sdf = SimpleDateFormat("d MMMM", Locale("ru"))
    val today = sdf.format(Date())
    val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    var timeForToday: String? = null

    mainViewModel.saveLocation(mainViewModel.currentLocation.value)

    if (location == "Хунзах") {
        if (dayOfWeek == Calendar.THURSDAY) {
            timeForToday = ReminderProvider.datesKhunzakhSalawat[today]
        } else if (dayOfWeek == Calendar.SUNDAY) {
            timeForToday = ReminderProvider.datesKhunzakhHatmu[today]
        }
    } else if (location == "Махачкала") {
        if (dayOfWeek == Calendar.THURSDAY) {
            timeForToday = ReminderProvider.datesMakhachkalaSalawat[today]
        } else if (dayOfWeek == Calendar.SUNDAY) {
            timeForToday = ReminderProvider.datesMakhachkalaHatmu[today]
        }
    }

    if (timeForToday != null) {
        AnimateContent(shortText = "В $timeForToday", longText = " Сегодня в $timeForToday")
    } else {
        AnimateContent(shortText = "Сегодня нет", longText = "Сегодня $today, нет Салават и Хатму")
    }
}

