package com.example.secretgifter2.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.viewmodel.MainViewModel

@Composable
fun WaitingRoomScreen(
    viewModel: MainViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.pollRoomStatus()
    }

    LaunchedEffect(Unit) {
        viewModel.pollParticipants()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Waiting room 📱",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Room code: ${viewModel.roomCode}"
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(viewModel.participants) {

                Text(
                    text = "• ${it.name}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        if (viewModel.isHost) {

            Button(
                onClick = {
                    viewModel.startSeparateDeviceGame()
                },
                enabled = viewModel.participants.size >= 2
            ) {
                Text("Start game")
            }
        }
    }
}