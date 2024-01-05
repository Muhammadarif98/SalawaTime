package com.example.salawatime.presentation.ui.screen.home.counter

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.salawatime.R
import com.example.salawatime.presentation.ui.theme.CountBackButton
import com.example.salawatime.presentation.ui.theme.CountDownButton
import com.example.salawatime.presentation.ui.theme.CountResetButton
import com.example.salawatime.presentation.ui.theme.OnClickButton
import com.example.salawatime.presentation.ui.theme.OutlinedText

@SuppressLint("UnrememberedMutableState")
@Composable
fun CounterUI(navController: NavController){
    val context = LocalContext.current
    val viewModel: CounterViewModel = viewModel()
    viewModel.setupSharedPreferences(context)
    var count by mutableIntStateOf(viewModel.count.value)
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.click)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 282.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically(initialOffsetY = { it }) + fadeIn() togetherWith slideOutVertically(
                        targetOffsetY = { -it }) + fadeOut()
                } else {
                    slideInVertically(initialOffsetY = { -it }) + fadeIn() togetherWith slideOutVertically(
                        targetOffsetY = { it }) + fadeOut()
                }.using(SizeTransform(clip = false))
            },
            label = "AnimateIncrementDecrementExample"
        ) { targetCount ->
            OutlinedText(text = "$targetCount",
                color = Color(0xFFD8BB8B),
                outlineColor = Color(0xFF60594D)
            )
        }
    }
    Column() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, start = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OnClickButton(
                onClick = {
                    viewModel.incrementCounter()
                    count = viewModel.count.value
                    mediaPlayer.start()
                },
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier
                .padding(top = 4.dp)) {
                NavigateBackButton(navController = navController)
            }
            CountDownButton(onClick =
            {   viewModel.decrementCounter()
                count = viewModel.count.value
            })
            CountResetButton (onClick =
            {   viewModel.resetCounter()
                count = viewModel.count.value
            })
        }
    }
}

@Composable
private fun NavigateBackButton(navController: NavController) {
    CountBackButton (onClick =
    {   navController.popBackStack() })
}
