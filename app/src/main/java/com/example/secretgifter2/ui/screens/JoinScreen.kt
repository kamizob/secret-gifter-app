package com.example.secretgifter2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.viewmodel.MainViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.secretgifter2.ui.components.AppTextField

@Composable
fun JoinScreen(
    viewModel: MainViewModel
) {
    var code by remember { mutableStateOf("") }
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
            text = "Join room",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppTextField(
            value = code,
            onValueChange = {
                code = it.uppercase().take(6)
            },
            label = "Room code"
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
                viewModel.joinRoom(code, name, wishlistItems)
            },
            enabled = code.isNotBlank()
        ) {
            Text("Join")
        }
    }
}