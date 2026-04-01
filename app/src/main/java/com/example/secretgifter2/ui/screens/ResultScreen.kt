package com.example.secretgifter2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.viewmodel.MainViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button

@Composable
fun ResultScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("All done!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(viewModel.pairs.toList()) { (giver, receiver) ->
                Text(
                    "🎁 $giver → $receiver",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                viewModel.resetToSetup()
            }) {
                Text("Start over")
            }
            Button(onClick = {
                viewModel.currentScreen = "GAME"
                viewModel.restartGame()

            }) {
                Text("Play again")
            }

        }

    }

}