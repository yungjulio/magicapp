package com.example.magicapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.magicapp.CardViewModel
import com.example.magicapp.R
import com.example.magicapp.ui.screens.CardDisplayScreen
import com.example.magicapp.ui.screens.SavedCardsScreen
import com.example.magicapp.ui.screens.SearchScreen

@Composable
fun MagicApp(viewModel: CardViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Magic: The Gathering") },
                navigationIcon = if (currentDestination != "search") {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else null
            )
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "search", Modifier.padding(innerPadding)) {
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
