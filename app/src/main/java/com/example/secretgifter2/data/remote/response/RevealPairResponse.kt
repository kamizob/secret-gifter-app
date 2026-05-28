package com.example.secretgifter2.data.remote.response

data class RevealPairResponse(
    val giver: String,
    val receiver: String,
    val wishList: List<WishlistItemResponse>
)
