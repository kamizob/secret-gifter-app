package com.example.secretgifter2.data.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Participant(
    val id: Int,
    val publicId: String,
    val name: String,
    val wishlist: SnapshotStateList<String> = mutableStateListOf()
)
