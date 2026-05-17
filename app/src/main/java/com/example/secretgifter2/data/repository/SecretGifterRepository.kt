package com.example.secretgifter2.data.repository

import com.example.secretgifter2.data.api.RetrofitClient
import com.example.secretgifter2.data.remote.request.CreateParticipantRequest
import com.example.secretgifter2.data.remote.request.CreateWishlistItemRequest
import com.example.secretgifter2.data.remote.request.GeneratePairsRequest

class SecretGifterRepository {
    private val api = RetrofitClient.api

    suspend fun createRoom() =
        api.createRoom()

    suspend fun createParticipant(name: String, roomId: Int) =
        api.createParticipant(CreateParticipantRequest(name, roomId))

    suspend fun generatePairs(roomId: Int) =
        api.generatePairs(GeneratePairsRequest(roomId))

    suspend fun revealPair(publicId: String, roomId: Int) =
        api.revealPair(publicId, roomId)

    suspend fun createWishlistItem(participantPublicId: String, itemText: String) =
        api.createWishlistItem(
            CreateWishlistItemRequest(participantPublicId, itemText)
        )
    suspend fun deleteParticipant(id: Int) =
        api.deleteParticipant(id)
}