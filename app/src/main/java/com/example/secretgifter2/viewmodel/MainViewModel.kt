package com.example.secretgifter2.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.secretgifter2.data.model.Participant
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.secretgifter2.data.repository.SecretGifterRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import com.example.secretgifter2.data.remote.response.PairResponse
import com.example.secretgifter2.data.remote.response.WishlistItemResponse

class MainViewModel : ViewModel() {
    var currentScreen by mutableStateOf("SPLASH")
    private val repository = SecretGifterRepository()


    fun startGame() {
        val currentRoomId = roomId ?: return
        viewModelScope.launch {
            try {
                val response = repository.generatePairs(currentRoomId)
                pairs = response.associate { it.giverName to it.receiverName }
                currentScreen = "GAME"
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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
        val currentRoomId = roomId ?: return
        viewModelScope.launch {
            try {
                val response = repository.createParticipant(trimmed, currentRoomId)
                participants.add(Participant(
                    id = response.id,
                    publicId = response.publicId,
                    name = response.name))
                errorMessage = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    var pairs by mutableStateOf<Map<String, String>>(emptyMap())
        private set

//    fun generatePairs() {
//        val currentRoomId = roomId ?: return
//        viewModelScope.launch {
//            try {
//                val response = repository.generatePairs(currentRoomId)
//                pairs = response.associate { it.giverName to it.receiverName }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//    }
    var selectedPerson by mutableStateOf<String?>(null)
        private set

    var currentPlayerIndex by mutableStateOf(0)
        private set

    val currentPlayer: String?
        get() = participants.getOrNull(currentPlayerIndex)?.name

    fun nextPlayer() {
        isRevealLoaded = false
        currentPlayerIndex++
        revealedReceiver = null
        revealedWishlist = emptyList()
        if (currentPlayerIndex >= participants.size) {
            currentScreen = "RESULT"
        } else {
            selectedPerson = currentPlayer
        }

    }
    fun resetToSetup() {
        participants.clear()
        pairs = emptyMap()
        revealedReceiver = null
        revealedWishlist = emptyList()
        isRevealLoaded = false
        isRevealLoading = false
        currentPlayerIndex = 0
        selectedPerson = null
        roomId = null
        roomCode = null
        currentScreen = "SETUP"
        createRoom()
    }
//    fun resetToSetup() {
//        isRevealLoaded = false
//        isRevealLoading = false
//        revealedReceiver = null
//        revealedWishlist = emptyList()
//        currentScreen = "SETUP"
//        currentPlayerIndex = 0
//        selectedPerson = null
//    }
    fun removeParticipant(participant: Participant) {
        viewModelScope.launch {
            try {
                repository.deleteParticipant(participant.id)
                participants.remove(participant)
                pairs = emptyMap()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    var errorMessage by mutableStateOf<String?>(null)
        private set
    fun restartGame() {
        currentPlayerIndex = 0
        startGame()
    }
//    fun restartGame() {
//        currentPlayerIndex = 0
//        generatePairs()
//        currentScreen = "GAME"
//    }

    var roomId by mutableStateOf<Int?>(null)
        private set

    var roomCode by mutableStateOf<String?>(null)
        private set
    fun createRoom() {

        viewModelScope.launch {

            try {

                val response = repository.createRoom()

                roomId = response.id
                roomCode = response.code
                println("ROOM CREATED: ${response.id} ${response.code}")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    var revealedReceiver by mutableStateOf<String?>(null)
        private set
    var isRevealLoading by mutableStateOf(false)
        private set

    var isRevealLoaded by mutableStateOf(false)
        private set

    var revealedWishlist by mutableStateOf<List<WishlistItemResponse>>(emptyList())
        private set
    fun revealForCurrentPlayer() {
        val player = participants.getOrNull(currentPlayerIndex) ?: return
        val currentRoomId = roomId ?: return
        isRevealLoading = true
        isRevealLoaded = false
        revealedReceiver = null
        revealedWishlist = emptyList()
        viewModelScope.launch {
            try {
                val response = repository.revealPair(
                    player.publicId,
                    currentRoomId
                )
                revealedReceiver = response.receiver
                revealedWishlist = response.wishlist ?: emptyList()
                isRevealLoaded = true
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isRevealLoading = false
            }
        }
    }
//    fun revealForCurrentPlayer () {
//        val player = participants.getOrNull(currentPlayerIndex) ?: return
//        val currentRoomId = roomId ?: return
//        viewModelScope.launch {
//            try {
//                val response = repository.revealPair(player.publicId, currentRoomId)
//                revealedReceiver = response.receiver
//                revealedWishlist = response.wishlist ?: emptyList()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
    fun addWishlistItem (participant: Participant, itemText: String) {
        val trimmed = itemText.trim()
        if (trimmed.isBlank()) return

        viewModelScope.launch {
            try {
                repository.createWishlistItem(
                    participant.publicId,
                    trimmed
                    )
                participant.wishlist.add(trimmed)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



}