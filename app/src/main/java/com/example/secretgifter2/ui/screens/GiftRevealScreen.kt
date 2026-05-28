package com.example.secretgifter2.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.viewmodel.MainViewModel

@Composable
fun GiftRevealScreen(viewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        viewModel.revealMyself()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🎁 ${viewModel.myName}, your person is...",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = viewModel.revealedReceiver ?: "...",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))
        when {

            viewModel.isRevealLoading -> {

                CircularProgressIndicator()
            }

            viewModel.revealedWishlist.isNotEmpty() -> {

                Text(
                    "Their wishlist:",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                viewModel.revealedWishlist.forEach {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),

                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {

                        Text(
                            text = "• ${it.itemText}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            else -> {

                Text(
                    text = "No wishlist items yet.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        }
        if (viewModel.isHost) {

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.finishOnePhoneGame {
                        viewModel.loadPairs()
                        viewModel.currentScreen = "RESULT"
                    }
                }
            ) {
                Text("Finish game")
            }
        }
        else {
            Button(
                onClick = {

                    viewModel.saveCurrentRoomLocally()

                    viewModel.goHome()
                }
            ) {
                Text("Home")
            }
        }

//        if (viewModel.revealedWishlist.isNotEmpty()) {
//            Text("Their wishlist:", style = MaterialTheme.typography.titleMedium)
//            Spacer(modifier = Modifier.height(12.dp))
//            viewModel.revealedWishlist.forEach {
//                Text(
//                    text = "• ${it.itemText}",
//                    style = MaterialTheme.typography.bodyLarge,
//                    modifier = Modifier.padding(vertical = 4.dp)
//                )
//            }
//        } else {
//            Text(
//                text = "No wishlist items yet.",
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
//            )
//        }
    }
}