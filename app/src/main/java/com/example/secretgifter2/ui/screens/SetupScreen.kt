package com.example.secretgifter2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.ui.components.ParticipantInput
import com.example.secretgifter2.ui.components.ParticipantList
import com.example.secretgifter2.viewmodel.MainViewModel
import androidx.compose.ui.graphics.Color

@Composable
fun SetupScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        ParticipantInput {
            viewModel.addParticipant(it)
        }
        viewModel.errorMessage?.let {
            Text(
                text = it,
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        ParticipantList(
            participants = viewModel.participants,
            onDelete = {viewModel.removeParticipant(it)},
            modifier = Modifier.weight(1f)
        )
        Text("Participants: ${viewModel.participants.size}")
        Button(
            onClick = { viewModel.startGame()},
            enabled = viewModel.participants.size >= 2
        ) {
            Text("Let's start")
        }
    }
}