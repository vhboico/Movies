package com.example.movies.view

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.ui.theme.black
import com.example.movies.ui.theme.red
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController
){
    LaunchedEffect(key1 = true){
        delay(2500)
        navController.navigate("loginScreen")
    }
    Splash()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Splash(){

    Scaffold(
        modifier = Modifier.background(black),
        backgroundColor = Color.Transparent
    ) {

        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Movies",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = red
            )
            Circular()
        }
    }
}

@Composable
fun Circular(){
    val progress = 0.99f
    val infinite = rememberInfiniteTransition(label = "")

    val animation by infinite.animateFloat(
        initialValue = 0.0f,
        targetValue = progress,
        animationSpec = infiniteRepeatable(animation = tween(900)), label = "")

    CircularProgressIndicator(
        progress = animation,
        color = red,
        modifier = Modifier.padding(top = 15.dp).size(35.dp),
        strokeWidth = 5.dp
    )
}
