package com.example.magicapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.magicapp.data.remote.Card
import com.example.magicapp.ui.theme.Planewalker

@Composable
fun CardDisplayScreen(card: Card) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = card.name,
                            style = TextStyle(
                                fontFamily = Planewalker,
                                fontWeight = FontWeight.Normal,
                                fontSize = 27.sp,
                                brush = Brush.linearGradient(
                                    colors = listOf(Color.Gray, Color.LightGray),
                                    tileMode = TileMode.Clamp
                                )
                            )
                        )
                    }
                },
                backgroundColor = Color(0xFF121212), // Black background color
                contentColor = Color.White,
                elevation = 8.dp,
                modifier = Modifier.height(80.dp) // Adjust height if necessary
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.Gray, Color.LightGray),
                            tileMode = TileMode.Clamp
                        )
                    )
                    .padding(16.dp), // Additional padding if needed
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                card.imageUrl?.let { imageUrl ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
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
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Fit // Ensures the image fits within the box
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Text(
                    text = "Type: ${card.type}",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Rarity: ${card.rarity}",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                card.power?.let {
                    Text(
                        text = "Power: $it",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                card.toughness?.let {
                    Text(
                        text = "Toughness: $it",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                card.legality?.let {
                    Text(
                        text = "Legality:",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    it.forEach { legality ->
                        Text(
                            text = "${legality.format}: ${legality.legality}",
                            style = MaterialTheme.typography.body2,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }

                card.text?.let {
                    Text(
                        text = "Text: $it",
                        style = MaterialTheme.typography.body2,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}
