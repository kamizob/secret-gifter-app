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

    suspend fun joinRoom(code: String) =
        api.joinRoom(code)

    suspend fun getParticipants(roomId: Int) =
        api.getParticipants(roomId)

    suspend fun getPairs(roomId: Int) =
        api.getPairs(roomId)

    suspend fun startRoom(roomId: Int) =
        api.startRoom(roomId)

    suspend fun getRoomStatus(roomId: Int) =
        api.getRoomStatus(roomId)
}
