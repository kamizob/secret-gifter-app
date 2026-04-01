package com.example.secretgifter2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.R
import com.example.secretgifter2.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(viewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        delay(2000)
        viewModel.currentScreen = "SETUP"
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.secret_gifter_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)

        )
    }
}