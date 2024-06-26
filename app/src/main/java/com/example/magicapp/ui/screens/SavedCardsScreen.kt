package com.example.magicapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.magicapp.data.local.CardEntity
import com.example.magicapp.ui.theme.Planewalker

@Composable
fun SavedCardsScreen(cards: List<CardEntity>, onCardClicked: (CardEntity) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredCards = cards.filter { it.name.contains(searchQuery, ignoreCase = true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Saved Cards",
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
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Filter") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFFA726),
                        unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color(0xFFFFA726),
                        focusedLabelColor = Color(0xFFFFA726),
                        unfocusedLabelColor = Color.DarkGray
                    )
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = innerPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                        top = innerPadding.calculateTopPadding() + 8.dp,
                        end = innerPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                        bottom = innerPadding.calculateBottomPadding() + 8.dp
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color.Gray, Color.LightGray),
                                tileMode = TileMode.Clamp
                            )
                        )
                ) {
                    items(filteredCards) { card ->
                        CardGridItem(card = card, onClick = { onCardClicked(card) })
                    }
                }
            }
        }
    )
}

@Composable
fun CardGridItem(card: CardEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().background(brush = Brush.linearGradient(
                colors = listOf(Color.Gray, Color.LightGray),
                tileMode = TileMode.Clamp
            ))
        ) {
            card.imageUrl?.let { imageUrl ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.0f)
                        .clip(RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center,
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
                            .clip(RoundedCornerShape(8.dp)).padding(top = 17.dp, bottom = 10.dp),
                        contentScale = ContentScale.Fit,
                    )
                }
            }
//            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = card.name,
                style = MaterialTheme.typography.body1.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()

            )
        }
    }
}
