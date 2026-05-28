package com.example.secretgifter2.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.ui.components.AppTextField
import com.example.secretgifter2.viewmodel.MainViewModel

@Composable
fun HostSetupScreen(
    viewModel: MainViewModel
) {

    var name by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Create room 🎁",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Room code: ${viewModel.roomCode}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = "Your name"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.createHostParticipant(name)
            },
            enabled = name.isNotBlank()
        ) {
            Text("Continue")
        }
    }
}