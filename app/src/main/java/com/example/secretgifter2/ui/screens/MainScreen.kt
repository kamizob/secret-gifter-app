package com.example.secretgifter2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.R
import com.example.secretgifter2.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (viewModel.currentScreen != "SPLASH") {
                Image(
                    painter = painterResource(id = R.drawable.secret_gifter_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(24.dp)
                )
            }

            when (viewModel.currentScreen) {
                "SPLASH" -> SplashScreen(viewModel)
                "SETUP" -> SetupScreen(viewModel)
                "GAME" -> GameScreen(viewModel)
                "RESULT" -> ResultScreen(viewModel)
            }
        }

    }

}