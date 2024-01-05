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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.salawatime.R
import com.example.salawatime.presentation.ui.theme.CountBackButton
import com.example.salawatime.presentation.ui.theme.OnClickButton
import com.example.salawatime.presentation.ui.theme.CountDownButton
import com.example.salawatime.presentation.ui.theme.CountResetButton
import com.example.salawatime.presentation.ui.theme.OutlinedText

@SuppressLint("UnrememberedMutableState", "RememberReturnType", "SuspiciousIndentation")
@Composable
fun CounterScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.counterscreen),
                contentScale = ContentScale.FillBounds
            )
            .padding(0.dp),
        ) {
       CounterUI(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    val navController = rememberNavController()
    CounterScreen(navController = navController)
}
