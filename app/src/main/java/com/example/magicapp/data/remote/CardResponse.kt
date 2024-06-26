package com.example.magicapp.data.remote

data class CardResponse(
    val cards: List<Card>
)

data class Card(
    val name: String,
    val imageUrl: String?,
    val type: String,
    val rarity: String
)
