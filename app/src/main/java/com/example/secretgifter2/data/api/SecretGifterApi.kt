package com.example.secretgifter2.data.api

import com.example.secretgifter2.data.remote.request.CreateParticipantRequest
import com.example.secretgifter2.data.remote.request.GeneratePairsRequest
import com.example.secretgifter2.data.remote.response.CreateRoomResponse
import com.example.secretgifter2.data.remote.response.PairResponse
import com.example.secretgifter2.data.remote.response.ParticipantResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SecretGifterApi {
    @POST("api/rooms")
    suspend fun createRoom(): CreateRoomResponse

    @POST("api/participants")
    suspend fun createParticipant(
        @Body request: CreateParticipantRequest
    ): ParticipantResponse

    @POST("api/pairs/generate")
    suspend fun generatePairs(
        @Body request: GeneratePairsRequest
    ): List<PairResponse>
}