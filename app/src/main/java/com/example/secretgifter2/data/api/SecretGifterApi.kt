package com.example.secretgifter2.data.api

import com.example.secretgifter2.data.remote.request.CreateParticipantRequest
import com.example.secretgifter2.data.remote.request.CreateWishlistItemRequest
import com.example.secretgifter2.data.remote.request.GeneratePairsRequest
import com.example.secretgifter2.data.remote.response.CreateRoomResponse
import com.example.secretgifter2.data.remote.response.JoinRoomResponse
import com.example.secretgifter2.data.remote.response.PairResponse
import com.example.secretgifter2.data.remote.response.ParticipantResponse
import com.example.secretgifter2.data.remote.response.RevealPairResponse
import com.example.secretgifter2.data.remote.response.WishlistItemResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("api/pairs/reveal")
    suspend fun revealPair(
        @Query("publicId") publicId: String,
        @Query("roomId") roomId: Int
    ): RevealPairResponse

    @POST("api/wishlist")
    suspend fun createWishlistItem(
        @Body request: CreateWishlistItemRequest
    ) : WishlistItemResponse

    @DELETE("api/participants/{id}")
    suspend fun deleteParticipant(
        @Path("id") id: Int
    )

    @POST("api/rooms/join")
    suspend fun joinRoom(
        @Query("code") code: String
    ): JoinRoomResponse

    @GET("api/participants")
    suspend fun getParticipants(
        @Query("roomId") roomId: Int
    ): List<ParticipantResponse>

    @GET("api/pairs/{roomId}")
    suspend fun getPairs(
        @Path("roomId") roomId: Int
    ): List<PairResponse>

    @POST("api/rooms/{roomId}/start")
    suspend fun startRoom(
        @Path("roomId") roomId: Int
    )

    @GET("api/rooms/{roomId}/status")
    suspend fun getRoomStatus(
        @Path("roomId") roomId: Int
    ): String
}