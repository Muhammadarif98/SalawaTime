package com.example.salawatime.presentation.ui.screen.home.timer

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.salawatime.R
import com.example.salawatime.presentation.ui.theme.FifteenTimerButton
import com.example.salawatime.presentation.ui.theme.FiveTimerButton
import com.example.salawatime.presentation.ui.theme.OutlinedTextTimer
import com.example.salawatime.presentation.ui.theme.TenTimerButton
import com.example.salawatime.presentation.ui.theme.TimerBackButton
import com.example.salawatime.presentation.ui.theme.TimerPlayPauseButton
import com.example.salawatime.presentation.ui.theme.TimerResetButton
import kotlinx.coroutines.delay

@Composable
fun TimerScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.timerscreen),
                contentScale = ContentScale.FillBounds
            )
            .padding(0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        CountdownTimer(navController = navController)
    }
}

@Composable
fun CountdownTimer(navController: NavController) {
    var timeLeft by remember { mutableIntStateOf(5 * 60) } // 5 минут в секундах
    var started by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.alarm)
    }

    LaunchedEffect(key1 = timeLeft, key2 = started) {

        while (timeLeft > 0 && started) {
            delay(950L) // Задержка в 1 секунду
            timeLeft--
        }
        // Если таймер закончился, воспроизведите звук
        if (timeLeft == 0) {
            mediaPlayer.start()
        }
    }

    val minutes = "${timeLeft / 60}".padStart(2, '0')
    val seconds = "${timeLeft % 60}".padStart(2, '0')

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp, top = 160.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AnimatedContent(
                targetState = minutes,
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
                OutlinedTextTimer(
                    text = "$targetCount :",
                    color = Color(0xFFD8BB8B),
                    outlineColor = Color(0xFF60594D)
                )
            }
            AnimatedContent(
                targetState = " $seconds",
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
                OutlinedTextTimer(
                    text = targetCount,
                    color = Color(0xFFD8BB8B),
                    outlineColor = Color(0xFF60594D)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 50.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            FiveTimerButton(onClick = {
                timeLeft = 5 * 60
                started = false
            }
            )
            TenTimerButton(onClick = {
                timeLeft = 10 * 60
                started = false
            }
            )
            FifteenTimerButton(onClick = {
                timeLeft = 15 * 60
                started = false
            }
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            NavigateBackButton(navController)
            TimerPlayPauseButton(started = started, onClick = {
                started = !started})

            TimerResetButton(onClick = {
                timeLeft = 5 * 60
                started = false
                mediaPlayer.start()
            })
        }
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
          InfoTimerDialog()
        }
    }
}
@Composable
fun InfoTimerDialog() {
    var showDialog by remember { mutableStateOf(false) }

    Text(
        text = "Важно",
        modifier = Modifier.clickable { showDialog = true },
        color = Color(0xFF604D2E),
        fontSize = 16.sp,
        textDecoration = TextDecoration.Underline,
    )

    if (showDialog) {
        AlertDialog(
            containerColor = Color(0xFFF1E4D1),
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "Внимание",
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.infoTimer),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF827868)
                    ),
                ) {
                    Text("Ок", style = MaterialTheme.typography.bodyLarge)
                }
            },
        )
    }
}

@Composable
private fun NavigateBackButton(navController: NavController) {
    val showingDialog = remember { mutableStateOf(false) }
    if (showingDialog.value) {
        AlertDialog(
            containerColor = Color(0xFFF1E4D1),
            onDismissRequest = {
                showingDialog.value = false
            },
            text = {
                Text(
                    text = stringResource(id = R.string.text_args, 1),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            title = {
                Text(
                    fontSize = 40.sp,
                    text = stringResource(id = R.string.alertTitle),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            dismissButton = {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(onClick = {
                            showingDialog.value = false
                        })
                )
            },
            confirmButton = {
                Text(
                    color = Color(0xFF604D2E),
                    text = stringResource(id = R.string.close),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(onClick = {
                            showingDialog.value = false
                            navController.popBackStack()
                        })
                )
            },
        )
    }
    TimerBackButton(onClick =  {
        showingDialog.value = true}
    )
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    val navController = rememberNavController()
    TimerScreen(navController = navController)
}
