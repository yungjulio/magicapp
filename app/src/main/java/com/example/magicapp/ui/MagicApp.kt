package com.example.magicapp.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.magicapp.CardViewModel
import com.example.magicapp.ui.screens.CardDisplayScreen
import com.example.magicapp.ui.screens.SavedCardsScreen
import com.example.magicapp.ui.screens.SearchScreen

@Composable
fun MagicApp(viewModel: CardViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "search") {
        composable("search") {
            SearchScreen(viewModel) {
                navController.navigate("cardDisplay")
            }
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
