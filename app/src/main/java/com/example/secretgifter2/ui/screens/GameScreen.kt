package com.example.secretgifter2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.ui.components.Wheel
import com.example.secretgifter2.viewmodel.MainViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun GameScreen(viewModel: MainViewModel) {
    val player = viewModel.currentPlayer
    var showResult by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Give phone to: ${player ?: "-"}",
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (showResult && player != null) {
            val receiver = viewModel.pairs[player]
            if (receiver != null) {
                Text("You gift for: $receiver")
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        Wheel(
            names = viewModel.participants.map { it.name },
            targetName = player?.let {
                viewModel.pairs[it]
            },
            onFinished = {
                showResult = true
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Button(onClick = {
                viewModel.resetToSetup()
            }) {
                Text("Friends list")
            }
            Button(onClick = {
                showResult = false
                viewModel.nextPlayer()
            }) {
                Text("Next person")
            }

        }


    }
}