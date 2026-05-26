package com.example.secretgifter2.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.secretgifter2.viewmodel.MainViewModel

@Composable
fun HistoryScreen(
    viewModel: MainViewModel
) {

    val rooms = viewModel.getSavedRooms()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "Past games 🎁",
            style = MaterialTheme.typography.headlineMedium
        )

        LazyColumn {

            items(rooms) { room ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),

                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Text(
                            text = "Room: ${room.roomCode}",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Button(
                            onClick = {
                                viewModel.openSavedRoom(room)
                            }
                        ) {
                            Text("Open")
                        }
                    }
                }
            }
        }
    }
}