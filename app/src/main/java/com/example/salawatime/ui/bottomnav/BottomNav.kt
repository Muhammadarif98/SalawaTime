package com.example.salawatime.ui.bottomnav


import android.content.res.Resources.Theme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.salawatime.ui.theme.Brown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(modifier: Modifier) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.padding(bottom = 20.dp),
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        Modifier
            .padding(it)
        BottomNavGraph(
            navController = navController
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.ToDo,
        BottomBarScreen.Home,
        BottomBarScreen.Reminder


    )

    val gr1 = Color(0xFFE5D9A3)
    val gr2 = Color(0xFFF6EFCF)


    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination
    Box(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, top = 16.dp, bottom = 16.dp)
            .shadow(10.dp, shape = RoundedCornerShape(80.dp))
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(80.dp, 80.dp, 80.dp, 80.dp))
                .shadow(10.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            gr1,
                            gr2,
                        )
                    )
                ),


            ) {
            Row(
                modifier = Modifier
                    .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
                    .width(360.dp)
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    val brownColor = Color(0xFF827868)
    val greyColor = Color(0xFFFFFFFF)


    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        if (selected) brownColor else Color.Transparent

    val contentColor =
        if (selected) greyColor else brownColor

    Box(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .fillMaxHeight()
            .background(background)
            .weight(1f)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier.align(Center),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon",
                tint = contentColor
            )

            /* AnimatedVisibility(visible = selected) {
                 Text(
                     text = screen.title,
                     color = contentColor
                 )
             }*/
        }
    }
}

@Composable
@Preview
fun BottomNavPreview() {
    BottomNav(modifier = Modifier)
}