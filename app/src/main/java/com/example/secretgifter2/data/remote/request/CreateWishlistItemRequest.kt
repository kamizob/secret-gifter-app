package com.example.secretgifter2.data.remote.request

data class CreateWishlistItemRequest(
    val participantPublicId: String,
    val itemText: String
)
