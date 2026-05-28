package com.example.secretgifter2.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.ui.components.AppTextField
import com.example.secretgifter2.viewmodel.MainViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

@Composable
fun HostSetupScreen(
    viewModel: MainViewModel
) {

    var name by remember {
        mutableStateOf("")
    }
    var wishlistInput by remember { mutableStateOf("") }
    var wishlistItems by remember { mutableStateOf(listOf<String>()) }

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
        Text("Your wishlist 🎁", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            AppTextField(
                value = wishlistInput,
                onValueChange = { wishlistInput = it },
                label = "Add item",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (wishlistInput.isNotBlank()) {
                        wishlistItems = wishlistItems + wishlistInput.trim()
                        wishlistInput = ""
                    }
                },
                enabled = wishlistInput.isNotBlank()
            ) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(wishlistItems) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("• $item", style = MaterialTheme.typography.bodyLarge)
                    IconButton(onClick = {
                        wishlistItems = wishlistItems - item
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Remove")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                viewModel.createHostParticipant(name, wishlistItems)
            },
            enabled = name.isNotBlank()
        ) {
            Text("Continue")
        }
    }
}