package com.example.salawatime.presentation.ui.screen.reminder

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.salawatime.presentation.ui.screen.reminder.component.ReminderList
import com.example.salawatime.presentation.worker.WorkViewModel
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun ReminderScreen(workViewModel: WorkViewModel) {
    Scaffold {
        ReminderList(workViewModel)
    }
}

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReminderList()
}
*/