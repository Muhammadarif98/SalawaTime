package com.example.salawatime.ui.bottomnav
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.salawatime.ui.screen.HomeScreen
import com.example.salawatime.ui.screen.ReminderScreen
import com.example.salawatime.ui.screen.ToDoScreen


@Composable
fun BottomNavGraph(

    navController: NavHostController
) {
    NavHost(

        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(route = BottomBarScreen.Reminder.route) {
            ReminderScreen()
        }
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.ToDo.route) {
            ToDoScreen()
        }
    }
}

