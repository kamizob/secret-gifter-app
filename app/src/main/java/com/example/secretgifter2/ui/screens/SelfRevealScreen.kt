package com.example.secretgifter2.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.ui.components.Wheel
import com.example.secretgifter2.viewmodel.MainViewModel

@Composable
fun SelfRevealScreen(
    viewModel: MainViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.revealMyself()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "🎁 Reveal your person",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (viewModel.isRevealLoaded && viewModel.spinFinished) {

            Text(
                text = "You gift for:",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = viewModel.revealedReceiver ?: "",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Wishlist 🎁",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            viewModel.revealedWishlist.forEach {

                Text(
                    text = "• ${it.itemText}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Wheel(
            names = viewModel.participants.map { it.name },
            targetName = if (viewModel.isRevealLoaded) viewModel.revealedReceiver else null,
            onFinished = {
                viewModel.spinFinished = true
            }
        )
        if (viewModel.spinFinished && viewModel.isRevealLoaded) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { viewModel.currentScreen = "GIFT_REVEAL" }) {
                Text("See your person 🎁")
            }
        }
    }
}