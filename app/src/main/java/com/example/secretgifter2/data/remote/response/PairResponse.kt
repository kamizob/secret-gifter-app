package com.example.secretgifter2.data.remote.response

data class PairResponse(
    val giverName: String,
    val receiverName: String,
    val wishList: List<WishlistItemResponse>
)
