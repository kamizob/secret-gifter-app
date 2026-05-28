package com.example.secretgifter2.ui.components


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {

    TextField(
        value = value,
        onValueChange = onValueChange,

        label = {
            Text(label)
        },

        modifier = modifier,

        colors = TextFieldDefaults.colors(

            focusedContainerColor =
                MaterialTheme.colorScheme.surface,

            unfocusedContainerColor =
                MaterialTheme.colorScheme.surface,

            focusedTextColor =
                MaterialTheme.colorScheme.onSurface,

            unfocusedTextColor =
                MaterialTheme.colorScheme.onSurface,

            focusedIndicatorColor =
                MaterialTheme.colorScheme.primary,

            unfocusedIndicatorColor =
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),

            focusedLabelColor =
                MaterialTheme.colorScheme.primary,

            unfocusedLabelColor =
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),

            cursorColor =
                MaterialTheme.colorScheme.primary
        )
    )
}