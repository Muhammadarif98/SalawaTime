package com.example.salawatime.presentation.ui.screen.reminder

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salawatime.data.reminder.ReminderDatabase

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReminderListItem(reminderDatabase: ReminderDatabase,
                     isChecked: Boolean,
                     onSwitchCheckedChange: (Boolean) -> Unit) {

    val gr1 = Color(0xFFF1E4D1)
    val gr2 = Color(0xFFFFFEFC)
    val gr3 = Color(0xFFF1E4D1)
    val gr4 = Color(0xFF604D2E)
    val st1 = Color(0xFFF4DBAD)
    val st2 = Color(0xFFFDFBCC)
    val st3 = Color(0xFFF9EBBD)
    val st4 = Color(0xFFF4DBAD)

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
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
        Row(
            Modifier
                .fillMaxSize()

                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            gr1,
                            gr2,
                            gr3,
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 26.dp,top = 16.dp)
                    .weight(1F)
                    .height(85.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = reminderDatabase.title,
                    color = gr4,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = reminderDatabase.description,
                    color = gr4,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Switch(
                modifier = Modifier.padding(32.dp),
                checked = isChecked,
                onCheckedChange = onSwitchCheckedChange,
                colors  = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFFFFF8D6),
                    checkedTrackColor = Color(0xFFBEA787),
                    uncheckedThumbColor = Color(0xFFBEA787),
                    uncheckedTrackColor= Color(0xFFFFF8D6),),
                )
        }
    }
}

