package com.example.salawatime.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.salawatime.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun HomeScreen() {
    val gr1 = Color(0xFFE0D3C3)
    val gr2 = Color(0xFFE4C8A6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        gr1,
                        gr2,
                    )
                )
            )
    ) {
        CardCounter()
        CardTimer()
    }

}
@Composable
fun CardCounter() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 50.dp,end = 30.dp,bottom = 15.dp)
            .shadow(
                10.dp, shape = RoundedCornerShape(50.dp), ambientColor = Color.Black,
                spotColor = Color.Black,
            )
            .clickable { },
        RoundedCornerShape(50.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
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
fun CardTimer (){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 15.dp,end = 30.dp,bottom = 30.dp)
            .shadow(
                10.dp, shape = RoundedCornerShape(50.dp), ambientColor = Color.Black,
                spotColor = Color.Black,
            )
            .clickable { },
        RoundedCornerShape(50.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.tim),
                contentDescription = "First Image",
                modifier = Modifier
                    .align(Alignment.Center)
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