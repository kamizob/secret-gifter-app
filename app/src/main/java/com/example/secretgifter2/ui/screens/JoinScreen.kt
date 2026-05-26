package com.example.secretgifter2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.viewmodel.MainViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun JoinScreen(
    viewModel: MainViewModel
) {
    var code by remember { mutableStateOf("") }
    var name by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Join room",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = code,
            onValueChange = {
                code = it.uppercase()
            },
            label = {
                Text("Room code")
            }
        )

        TextField(
            value = name,

            onValueChange = {
                name = it
            },

            label = {
                Text("Your name")
            }
        )


        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.joinRoom(code, name)
            },
            enabled = code.isNotBlank()
        ) {
            Text("Join")
        }
    }
}