package com.example.salawatime.presentation.ui.theme

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.salawatime.R
import com.example.salawatime.presentation.ui.screen.reminder.other.DropDownViewModel
import com.example.salawatime.presentation.ui.screen.reminder.other.saveSelectedText
import kotlinx.coroutines.launch


//HOME
//COUNTER
@Composable
fun OnClickButton(onClick: () -> Unit) {
    val image2: Painter = painterResource(id = R.drawable.onclickoff)
    val image1: Painter = painterResource(id = R.drawable.onclickon)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .size(250.dp)
    ) {
        Image(
            painter = if (isPressed) image1 else image2,
            contentDescription = "Описание изображения"
        )
    }
}

@Composable
fun CountDownButton(onClick: () -> Unit) {
    val image2: Painter = painterResource(id = R.drawable.counterdownbutton)
    val image1: Painter = painterResource(id = R.drawable.counterdownbuttonclick)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            modifier = Modifier.size(45.dp),
            painter = if (isPressed) image1 else image2,
            contentDescription = "Описание изображения"
        )
    }
}

@Composable
fun CountBackButton(onClick: () -> Unit) {
    val image2: Painter = painterResource(id = R.drawable.counterbackbutton)
    val image1: Painter = painterResource(id = R.drawable.counterbackbuttonclick)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = if (isPressed) image1 else image2,
            contentDescription = "Описание изображения"
        )
    }
}

@Composable
fun CountResetButton(onClick: () -> Unit) {
    val image2: Painter = painterResource(id = R.drawable.counterresetbutton)
    val image1: Painter = painterResource(id = R.drawable.counterresetbuttonclick)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            modifier = Modifier.size(43.dp),
            painter = if (isPressed) image1 else image2,
            contentDescription = "Описание изображения"
        )
    }
}

@Composable
fun OutlinedText(text: String, color: Color, outlineColor: Color) {
    val delta = 1.dp
    val directions = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)

    directions.forEach { (dx, dy) ->
        Text(
            text = text,
            color = outlineColor,
            fontSize = 130.sp,
            modifier = Modifier.offset(dx * delta, dy * delta),
            style = MaterialTheme.typography.titleLarge,
        )
    }

    Text(
        text = text,
        color = color,
        fontSize = 130.sp,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
fun OutlinedTextTimer(text: String, color: Color, outlineColor: Color) {
    val delta = 1.dp
    val directions = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)

    directions.forEach { (dx, dy) ->
        Text(
            text = text,
            color = outlineColor,
            fontSize = 100.sp,
            modifier = Modifier.offset(dx * delta, dy * delta),
            style = MaterialTheme.typography.titleLarge,
        )
    }

    Text(
        text = text,
        color = color,
        fontSize = 100.sp,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
fun SvgIcon() {
    val image: Painter = painterResource(id = R.drawable.location)
    Image(
        modifier = Modifier
            .size(22.dp),
        painter = image,
        colorFilter = ColorFilter.tint(Color(0xFF604D2E)),
        contentDescription = "Описание"
    )
}
//TIMER
@Composable
fun FiveTimerButton(onClick: () -> Unit) {
    val image2: Painter = painterResource(id = R.drawable.timerfiveof)
    val image1: Painter = painterResource(id = R.drawable.timerfiveon)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            modifier = Modifier.size(65.dp),
            painter = if (isPressed) image1 else image2,
            contentDescription = "Описание изображения"
        )
    }
}
@Composable
fun TenTimerButton(onClick: () -> Unit) {
    val image2: Painter = painterResource(id = R.drawable.timertenof)
    val image1: Painter = painterResource(id = R.drawable.timertenon)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            modifier = Modifier.size(65.dp),
            painter = if (isPressed) image1 else image2,
            contentDescription = "Описание изображения"
        )
    }
}
@Composable
fun FifteenTimerButton(onClick: () -> Unit) {
    val image2: Painter = painterResource(id = R.drawable.timerfifteenof)
    val image1: Painter = painterResource(id = R.drawable.timerfifteenon)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            modifier = Modifier.size(65.dp),
            painter = if (isPressed) image1 else image2,
            contentDescription = "Описание изображения"
        )
    }
}
@Composable
fun TimerBackButton(onClick: () -> Unit) {
    val image2: Painter = painterResource(id = R.drawable.timerbackof)
    val image1: Painter = painterResource(id = R.drawable.timerbackon)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            modifier = Modifier.size(65.dp),
            painter = if (isPressed) image1 else image2,
            contentDescription = "Описание изображения"
        )
    }
}
@Composable
fun TimerResetButton(onClick: () -> Unit) {
    val image2: Painter = painterResource(id = R.drawable.timerresetof)
    val image1: Painter = painterResource(id = R.drawable.timerreseton)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            modifier = Modifier.size(65.dp),
            painter = if (isPressed) image1 else image2,
            contentDescription = "Описание изображения"
        )
    }
}

@Composable
fun TimerPlayPauseButton(started: Boolean, onClick: () -> Unit) {
    val image1: Painter = painterResource(id = R.drawable.timerplayof)
    val image2: Painter = painterResource(id = R.drawable.timerplayon)
    val image3: Painter = painterResource(id = R.drawable.timerpauseof)
    val image4: Painter = painterResource(id = R.drawable.timerpauseon)

    var pressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                        onClick()
                    }
                )
            }
    ) {
        Image(
            modifier = Modifier.size(65.dp),
            painter = when {
                started && !pressed -> image3
                started && pressed -> image4
                !started && !pressed -> image1
                !started && pressed -> image2
                else -> image1
            },
            contentDescription = "Описание изображения"
        )
    }
}


//@Composable
//fun TimerPlayPauseButton(started:Boolean,onClick: () -> Unit) {
//    val image1: Painter = painterResource(id = R.drawable.timerplayof)
//    val image2: Painter = painterResource(id = R.drawable.timerplayon)
//    val image3: Painter = painterResource(id = R.drawable.timerpauseof)
//    val image4: Painter = painterResource(id = R.drawable.timerpauseon)
//
//    var imageState by remember { mutableStateOf(0) }
//    var pressed by remember { mutableStateOf(false) }
//
//    Box(
//        modifier = Modifier
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onPress = {
//                        pressed = true
//                        imageState = (imageState + 1) % 4
//                        tryAwaitRelease()
//                        pressed = false
//                        imageState = (imageState + 1) % 4
//                        onClick()
//                    }
//                )
//            }
//    ) {
//        //if (started) "Стоп" else "Старт",
//        Image(
//            modifier = Modifier.size(45.dp),
//            painter = when (imageState) {
//                0 -> image1
//                1 -> image2
//                2 -> image3
//                3 -> image4
//                else -> image1
//            },
//            contentDescription = "Описание изображения"
//        )
//    }
//}
//



//REMINDER
@Composable
fun InfoDialog() {
    var showDialog by remember { mutableStateOf(false) }

    Text(
        text = "Инфо",
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
                    "Информация",
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.info),
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
fun AnimateContent(shortText: String, longText: String) {
    //  val shortText = stringResource(id = R.string.short_text)
    //  val longText = stringResource(id = R.string.long_text)
    var short by remember { mutableStateOf(true) }
    val st1 = Color(0xFFF4DBAD)
    val st2 = Color(0xFFFDFBCC)
    val st3 = Color(0xFFF9EBBD)
    val st4 = Color(0xFFF4DBAD)

    Box(
        modifier = Modifier
            .background(Color(0xFFF1E4D1), RoundedCornerShape(50.dp))
            .border(
                7.dp, brush = Brush.verticalGradient(
                    colors = listOf(
                        st1,
                        st2,
                        st3,
                        st4,
                    )
                ), shape = RoundedCornerShape(50.dp)
            )
            .clickable { short = !short }
            .padding(start = 25.dp, top = 10.dp, bottom = 10.dp, end = 25.dp)
            .wrapContentSize()
            .animateContentSize(
                tween(1000)
            ),

        ) {
        Text(
            if (short) {
                shortText
            } else {
                longText
            },

            softWrap = true,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFF604D2E),
                fontSize = 15.sp
            )
        )
    }
}

@Composable
fun DropdownSample(mainViewModel: DropDownViewModel) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("PREFERENCES_NAME", Context.MODE_PRIVATE)

    // Загрузка сохраненного значения при старте
    LaunchedEffect(Unit) {
        mainViewModel.selectedText.value =
            sharedPreferences.getString("selectedText", "Хунзах") ?: "Хунзах"
    }
    SvgIcon()
    DropdownMenuSample(mainViewModel, sharedPreferences)
}

@Composable
fun DropdownMenuSample(mainViewModel: DropDownViewModel, sharedPreferences: SharedPreferences) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    LazyColumn() {
        item {
            Column {
                Text(
                    mainViewModel.selectedText.value,
                    modifier = Modifier.clickable { expanded = true },
                    color = Color(0xFF604D2E),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.bodyLarge
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    DropdownMenuItem(onClick = {
                        mainViewModel.selectedText.value = "Хунзах"
                        mainViewModel.currentLocation.value = "Хунзах"
                        saveSelectedText(sharedPreferences, "Хунзах")
                        expanded = false
                        scope.launch {
                            Toast.makeText(context, "Выбрана локация Хунзах", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                        Text(
                            "Хунзах",
                            color = Color(0xFF604D2E),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    DropdownMenuItem(onClick = {
                        mainViewModel.selectedText.value = "Махачкала"
                        mainViewModel.currentLocation.value = "Махачкала"
                        saveSelectedText(sharedPreferences, "Махачкала")
                        expanded = false
                        scope.launch {
                            Toast.makeText(context, "Выбрана локация Махачкала", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                        Text(
                            "Махачкала",
                            color = Color(0xFF604D2E),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}