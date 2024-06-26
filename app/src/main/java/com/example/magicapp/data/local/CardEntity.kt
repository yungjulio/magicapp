package com.example.magicapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.magicapp.data.remote.Legality

@Entity
data class CardEntity(
    @PrimaryKey val name: String,
    val imageUrl: String?,
    val type: String,
    val rarity: String,
    val text: String?,
    val power: String?,
    val toughness: String?,
    val legality: List<Legality>?
)
