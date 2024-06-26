package com.example.magicapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.magicapp.data.remote.Card

@Composable
fun CardDisplayScreen(card: Card) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = card.name, style = androidx.compose.material.MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))

        card.imageUrl?.let {
            Image(
                painter = rememberImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(text = "Type: ${card.type}", style = androidx.compose.material.MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Rarity: ${card.rarity}", style = androidx.compose.material.MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(4.dp))

        // Add more card details as needed
    }
}
