package com.example.salawatime.presentation.ui.screen.reminder.component

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salawatime.data.reminder.SwitchInfo
import com.example.salawatime.presentation.ui.screen.reminder.other.DropDownViewModel
import com.example.salawatime.presentation.ui.screen.reminder.other.getSwitchState
import com.example.salawatime.presentation.ui.screen.reminder.other.saveSwitchState
import com.example.salawatime.presentation.ui.screen.reminder.notification.sendNotification
import java.util.*

@Composable
fun CardContent(info : SwitchInfo,
                mainViewModel: DropDownViewModel,
                sharedPreferences: SharedPreferences){
    val st1 = Color(0xFFF4DBAD)
    val st2 = Color(0xFFFDFBCC)
    val st3 = Color(0xFFF9EBBD)
    val st4 = Color(0xFFF4DBAD)

    Card(
        modifier = Modifier
            .padding(10.dp)
            .border(
                10.dp, brush = Brush.verticalGradient(
                    colors = listOf(
                        st1,
                        st2,
                        st3,
                        st4,
                    )
                ), shape = RoundedCornerShape(44.dp)
            ),
        RoundedCornerShape(44.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
    ) {
        CardUI(
            info = info,
            mainViewModel = mainViewModel ,
            sharedPreferences = sharedPreferences )
    }
}

@Composable
fun CardUI(info : SwitchInfo, mainViewModel: DropDownViewModel,
           sharedPreferences: SharedPreferences){
    val gr1 = Color(0xFFF1E4D1)
    val gr2 = Color(0xFFFFFEFC)
    val gr3 = Color(0xFFF1E4D1)
    val gr4 = Color(0xFF604D2E)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        gr1,
                        gr2,
                        gr3,
                    )
                )
            )
            .padding(top = 6.dp, bottom = 16.dp, start = 0.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 30.dp, top = 0.dp)
                    .weight(1F)
                    .height(85.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = info.title,
                    color = gr4,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Black
                )
                Column(
                    modifier = Modifier.padding(top = 8.dp)) {
                }
                Text(
                    text = "Напомнить за:",
                    color = gr4,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = info.description,
                    color = gr4,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
           SwitchForCardUi(
               info = info,
               mainViewModel = mainViewModel ,
               sharedPreferences = sharedPreferences)
        }
    }
}

@Composable
fun SwitchForCardUi(
    info : SwitchInfo,
    mainViewModel: DropDownViewModel,
    sharedPreferences: SharedPreferences){
    val context = LocalContext.current
    val switchState =
        remember { mutableStateOf(getSwitchState(context, info.switchKey)) }
    Switch(
        modifier = Modifier.padding(32.dp),
        checked = switchState.value,
        onCheckedChange = { isChecked ->
            switchState.value = isChecked
            saveSwitchState(context, info.switchKey, isChecked)
            // workViewModel.scheduleNotification()
            val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            if (isChecked) {
                if (dayOfWeek == Calendar.THURSDAY && info.title == "Салават") {
                    sendNotification(
                        context,
                        info.title,
                        info.description,
                        info.time,
                        mainViewModel.currentLocation.value,
                        sharedPreferences
                    )
                    mainViewModel.saveLocation(mainViewModel.currentLocation.value)
                    sharedPreferences.edit().putString("location", mainViewModel.currentLocation.value).apply()
                } else if (dayOfWeek == Calendar.SUNDAY && info.title == "Хатму") {
                    sendNotification(
                        context,
                        info.title,
                        info.description,
                        info.time,
                        mainViewModel.currentLocation.value,
                        sharedPreferences
                    )
                    mainViewModel.saveLocation(mainViewModel.currentLocation.value)
                    sharedPreferences.edit().putString("location", mainViewModel.currentLocation.value).apply()
                }
            }

        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color(0xFFFFF8D6),
            checkedTrackColor = Color(0xFFBEA787),
            uncheckedThumbColor = Color(0xFFBEA787),
            uncheckedTrackColor = Color(0xFFFFF8D6),
        ),
    )
}