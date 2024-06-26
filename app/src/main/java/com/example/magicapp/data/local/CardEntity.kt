package com.example.magicapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity(
    @PrimaryKey val name: String,
    val imageUrl: String?,
    val type: String,
    val rarity: String
)
