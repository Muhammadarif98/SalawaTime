package com.example.salawatime.presentation.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.salawatime.R


@Composable
@Preview
fun HomeScreen() {
    Box {
        NavigationExample()
    }
}

@Composable
fun CardCounter(
    listener: () -> Unit = { }
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 50.dp, end = 30.dp, bottom = 15.dp)
            .shadow(
                10.dp, shape = RoundedCornerShape(50.dp), ambientColor = Color.Black,
                spotColor = Color.Black,
            ),
        RoundedCornerShape(50.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick =
                    listener
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.cback),
                contentDescription = "First Image",
                modifier = Modifier
                    .align(Alignment.Center)


            )
            Image(
                painter = painterResource(id = R.drawable.counter),
                contentDescription = "Second Image",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp)
            )


        }
    }
}

@Composable
fun CardTimer(
    listener: () -> Unit = { }
) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 15.dp, end = 30.dp, bottom = 30.dp)
                .shadow(
                    10.dp, shape = RoundedCornerShape(50.dp), ambientColor = Color.Black,
                    spotColor = Color.Black,
                ),
            RoundedCornerShape(50.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick =
                        listener
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.g8),
                    contentDescription = "First Image",
                    modifier = Modifier
                        .align(Alignment.Center),

                    )
                Image(
                    painter = painterResource(id = R.drawable.timerr),
                    contentDescription = "Second Image",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)

                        .padding(bottom = 5.dp)
                )


            }
        }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NavigationExample() {
    val navController = rememberNavController()
    Scaffold {
        NavHost(navController, Screen.Home.route, Modifier) {
            composable(Screen.Home.route) { Home(navController) }
            composable(Screen.Counter.route) { CounterScreen(navController) }
            composable(Screen.Timer.route) { TimerScreen(navController) }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Counter : Screen("counter")
    object Timer : Screen("timer")

}

@Composable
fun Home(navController: NavHostController) {

    val gr1 = Color(0xFFE0D3C3)
    val gr2 = Color(0xFFE4C8A6)

    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        gr1,
                        gr2,
                    )
                )
            )
           // .then(Modifier.padding(8.dp))
    ) {

        NavigateCounter{
            navController.navigate(Screen.Counter.route)
        }

        NavigateTimer {
            navController.navigate(Screen.Timer.route)
        }


    }
}

@Composable
private fun NavigateTimer(
    listener: () -> Unit = { }
) {
    CardTimer(listener)
}
@Composable
private fun NavigateCounter(
    listener: () -> Unit = { }
) {
    CardCounter(listener)
}
