package com.example.salawatime.presentation.ui.bottomnav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.salawatime.presentation.ui.screen.home.HomeScreen
import com.example.salawatime.presentation.ui.screen.reminder.ReminderScreen
import com.example.salawatime.presentation.ui.screen.todo.ViewModel.MainViewModel
import com.example.salawatime.presentation.ui.screen.todo.ToDoScreen
import com.example.salawatime.presentation.worker.WorkViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    viewModel : MainViewModel,
    workViewModel : WorkViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Reminder.route) {
            ReminderScreen(workViewModel)
        }
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.ToDo.route) {
            ToDoScreen(viewModel)
        }
    }
}