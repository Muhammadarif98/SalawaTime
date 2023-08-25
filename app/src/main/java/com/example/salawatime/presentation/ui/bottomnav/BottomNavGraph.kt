package com.example.salawatime.presentation.ui.bottomnav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.salawatime.presentation.ui.screen.home.HomeScreen
import com.example.salawatime.presentation.ui.screen.reminder.ReminderScreen
import com.example.salawatime.presentation.ui.screen.reminder.ReminderViewModel
import com.example.salawatime.presentation.ui.screen.todo.MainViewModel
import com.example.salawatime.presentation.ui.screen.todo.ToDoScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    viewModel : MainViewModel,
    reminderViewModel: ReminderViewModel
) {
    val context2 = LocalContext.current
    NavHost(

        navController = navController,
        startDestination = BottomBarScreen.Home.route

    ) {

        composable(route = BottomBarScreen.Reminder.route) {
            ReminderScreen(reminderViewModel)
        }
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.ToDo.route) {
            ToDoScreen(viewModel)
           // context2.startActivity(Intent(context2 , TodoActivity::class.java))
        }


    }
}