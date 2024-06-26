package com.example.magicapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.magicapp.CardViewModel

@Composable
fun SearchScreen(viewModel: CardViewModel, navController: NavController) {
    val searchQuery = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text("Card Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(
                onClick = {
                    viewModel.searchCard(searchQuery.value)
                    navController.navigate("cardDisplay")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Retrieve")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    navController.navigate("savedCards")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Saved Cards")
            }
        }
    }
}
