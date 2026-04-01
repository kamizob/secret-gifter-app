package com.example.secretgifter2.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.secretgifter2.data.model.Participant
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainViewModel : ViewModel() {
    var currentScreen by mutableStateOf("SPLASH")


    fun startGame() {
        generatePairs()
        currentScreen = "GAME"
    }
    var participants = mutableStateListOf<Participant>()
        private set
    fun addParticipant(name: String) {
        val trimmed = name.trim()
        if (trimmed.isBlank()) return
        val exists = participants.any {
            it.name.equals(trimmed, ignoreCase = true)
        }
        if (exists) {
            errorMessage = "A participant with this name already exists"
            return
        }
        participants.add(Participant(trimmed))
        pairs = emptyMap()
        errorMessage = null
    }
    var pairs by mutableStateOf<Map<String, String>>(emptyMap())
        private set

    fun generatePairs() {
        if (participants.size < 2) return

        val shuffled = participants.shuffled()
        val result = mutableMapOf<String, String>()

        for (i in shuffled.indices) {
            val giver = shuffled[i].name
            val receiver = shuffled[(i + 1) % shuffled.size].name
            result[giver] = receiver
        }

        pairs = result
    }
    var selectedPerson by mutableStateOf<String?>(null)
        private set

    fun selectPerson(name: String) {
        selectedPerson = name
    }
    var currentPlayerIndex by mutableStateOf(0)
        private set

    val currentPlayer: String?
        get() = participants.getOrNull(currentPlayerIndex)?.name

    fun nextPlayer() {
        currentPlayerIndex++
        if (currentPlayerIndex >= participants.size) {
            currentScreen = "RESULT"
        } else {
            selectedPerson = currentPlayer
        }

    }
    fun resetToSetup() {
        currentScreen = "SETUP"
        currentPlayerIndex = 0
        selectedPerson = null
    }
    fun removeParticipant(participant: Participant) {
        participants.remove(participant)
        pairs = emptyMap()
    }
    var errorMessage by mutableStateOf<String?>(null)
        private set
    fun restartGame() {
        currentPlayerIndex = 0
        generatePairs()
        currentScreen = "GAME"
    }


}