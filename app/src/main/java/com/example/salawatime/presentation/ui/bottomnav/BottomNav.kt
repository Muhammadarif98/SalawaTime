package com.example.salawatime.presentation.ui.bottomnav

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.salawatime.presentation.ui.screen.todo.ViewModel.MainViewModel
import com.example.salawatime.presentation.worker.WorkViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BottomNav(viewModel : MainViewModel,workViewModel: WorkViewModel) {
    val navController = rememberNavController()
    val gr1 = Color(0xFFE0D3C3)
    val gr2 = Color(0xFFE4C8A6)
    Scaffold(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        gr1,
                        gr2,
                    )
                )
            )
            .padding(bottom = 0.dp),
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        Modifier
            .padding(it)
        BottomNavGraph(
            navController = navController,
            viewModel = viewModel,
            workViewModel = workViewModel
        )

    }
}

