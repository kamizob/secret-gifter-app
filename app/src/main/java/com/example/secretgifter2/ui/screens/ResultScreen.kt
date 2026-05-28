package com.example.secretgifter2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.viewmodel.MainViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.secretgifter2.data.remote.response.PairResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.AlertDialog

@Composable
fun ResultScreen(viewModel: MainViewModel) {
    var selectedPair by remember {
        mutableStateOf<PairResponse?>(null)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("All done! \uD83D\uDC4F", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(viewModel.pairDetails) { pair  ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
//                        Text(
//                            text = "$giver \uD83C\uDF81➡\uFE0F $receiver",
//                            style = MaterialTheme.typography.bodyLarge
//                        )
                        Column {

                            Text(
                                text = "${pair.giverName} 🎁➡️ ${pair.receiverName}",
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Button(
                                onClick = {
                                    selectedPair = pair
                                }
                            ) {
                                Text("Gift ideas")
                            }
                        }

                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                viewModel.resetToSetup()
            }) {
                Text("Start over")
            }
            Button(onClick = {
                viewModel.currentScreen = "GAME"
                viewModel.restartGame()

            }) {
                Text("Play again")
            }

        }
        Button(
            onClick = {
                viewModel.goHome()
            }
        ) {
            Text("Home")
        }

    }
    selectedPair?.let { pair ->

        AlertDialog(

            containerColor =
                MaterialTheme.colorScheme.surface,

            titleContentColor =
                MaterialTheme.colorScheme.onSurface,

            textContentColor =
                MaterialTheme.colorScheme.onSurface,

            onDismissRequest = {
                selectedPair = null
            },

            title = {
                Text(
                    text = "Gift ideas for ${pair.receiverName}"
                )
            },

            text = {

                Column {

                    if (pair.wishList.isEmpty()) {

                        Text(
                            text = "No gift ideas added"
                        )

                    } else {

                        pair.wishList.forEach {

                            Text(
                                text = "• ${it.itemText}"
                            )
                        }
                    }
                }
            },

            confirmButton = {

                Button(
                    onClick = {
                        selectedPair = null
                    }
                ) {
                    Text("Close")
                }
            }
        )
    }

}