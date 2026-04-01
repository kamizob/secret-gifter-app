package com.example.secretgifter2.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.material3.TextFieldDefaults


@Composable
fun ParticipantInput(onAdd: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter name") },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,

                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,

                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),

                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),

                cursorColor = MaterialTheme.colorScheme.primary
            )

        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(onClick = {
            onAdd(name)
            name = ""
        }) {
            Text("Add")
        }
    }
}