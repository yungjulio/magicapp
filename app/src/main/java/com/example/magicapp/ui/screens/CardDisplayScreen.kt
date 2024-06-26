package com.example.magicapp.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.magicapp.CardViewModel
import com.example.magicapp.data.remote.Card

@Composable
fun CardDisplayScreen(card: Card) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = card.name, style = androidx.compose.material.MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))

        card.imageUrl?.let { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .error(android.R.drawable.ic_menu_report_image)
                        .scale(Scale.FILL)
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.0f),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(text = "Type: ${card.type}", style = androidx.compose.material.MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Rarity: ${card.rarity}", style = androidx.compose.material.MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(4.dp))

        card.text?.let {
            Text(text = "Text: $it", style = androidx.compose.material.MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
        }

        card.power?.let {
            Text(text = "Power: $it", style = androidx.compose.material.MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
        }

        card.toughness?.let {
            Text(text = "Toughness: $it", style = androidx.compose.material.MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
        }

        card.legality?.let {
            Text(text = "Legality:", style = androidx.compose.material.MaterialTheme.typography.h6)
            it.forEach { legality ->
                Text(text = "${legality.format}: ${legality.legality}", style = androidx.compose.material.MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}
