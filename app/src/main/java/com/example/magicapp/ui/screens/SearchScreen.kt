package com.example.magicapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.magicapp.CardViewModel

@Composable
fun SearchScreen(viewModel: CardViewModel, onCardSelected: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Card Name") }
        )
        Button(onClick = {
            isLoading = true
            viewModel.searchCard(searchQuery)
            onCardSelected()
            isLoading = false
        }) {
            Text("Retrieve")
        }
        if (isLoading) {
            Text("Loading...")
        }
    }
}

