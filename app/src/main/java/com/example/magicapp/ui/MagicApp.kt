package com.example.magicapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.magicapp.CardViewModel
import com.example.magicapp.R
import com.example.magicapp.ui.screens.CardDisplayScreen
import com.example.magicapp.ui.screens.SavedCardsScreen
import com.example.magicapp.ui.screens.SearchScreen
import com.example.magicapp.ui.theme.Planewalker

@Composable
fun MagicApp(viewModel: CardViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF121212),
                contentColor = Color.White,
                elevation = 8.dp,
                modifier = Modifier.height(60.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (currentDestination != "search") {
                        IconButton(
                            onClick = { navController.navigateUp() },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                    Text(
                        text = "Magic: The Gathering",
                        style = TextStyle(
                            fontFamily = Planewalker,
                            fontWeight = FontWeight.Normal,
                            fontSize = 30.sp,
                            brush = Brush.linearGradient(
                                colors = listOf(Color.Gray, Color.LightGray),
                                tileMode = TileMode.Clamp
                            )
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .background(Color(0xFF121212)) // Custom non-pitch black background
                    .padding(innerPadding)
            ) {
                NavHost(navController, startDestination = "search") {
                    composable("search") {
                        SearchScreen(viewModel, navController)
                    }
                    composable("cardDisplay") {
                        val selectedCard by viewModel.selectedCard.observeAsState()
                        if (selectedCard != null) {
                            CardDisplayScreen(selectedCard!!)
                        } else {
                            Text("Loading...")
                        }
                    }
                    composable("savedCards") {
                        val cards by viewModel.allCards.observeAsState(emptyList())
                        SavedCardsScreen(cards) { cardEntity ->
                            viewModel.selectCard(cardEntity)
                            navController.navigate("cardDisplay")
                        }
                    }
                }
            }
        }
    )
}
