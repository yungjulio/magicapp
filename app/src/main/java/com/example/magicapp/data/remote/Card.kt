package com.example.magicapp.data.remote

data class Card(
    val name: String,
    val imageUrl: String?,
    val type: String,
    val rarity: String,
    val text: String?,
    val power: String?,
    val toughness: String?,
    val legality: List<Legality>?
)

data class Legality(
    val format: String,
    val legality: String
)
