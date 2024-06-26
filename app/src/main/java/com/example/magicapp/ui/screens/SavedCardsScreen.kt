package com.example.magicapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import com.example.magicapp.data.local.CardEntity

@Composable
fun SavedCardsScreen(cards: List<CardEntity>, onCardClicked: (CardEntity) -> Unit) {
    LazyColumn {
        items(cards) { card ->
            Card(card = card) {
                onCardClicked(card)
            }
        }
    }
}

@Composable
fun Card(card: CardEntity, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable { onClick() }) {
        Text(card.name)
        card.imageUrl?.let { Image(painter = rememberImagePainter(it), contentDescription = null) }
    }
}
