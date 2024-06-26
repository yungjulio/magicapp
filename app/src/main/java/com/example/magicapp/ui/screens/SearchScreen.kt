package com.example.magicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.magicapp.CardViewModel
import com.example.magicapp.ui.theme.Planewalker

@Composable
fun SearchScreen(viewModel: CardViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Find Cards",
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
                modifier = Modifier.height(60.dp) // Adjust height if necessary
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Use innerPadding here
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.Gray, Color.LightGray),
                            tileMode = TileMode.Clamp
                        )
                    )
                    .padding(16.dp) // Additional padding if needed
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Card Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFFA726),
                        unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color(0xFFFFA726),
                        focusedLabelColor = Color(0xFFFFA726),
                        unfocusedLabelColor = Color.DarkGray
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Button(
                        onClick = {
                            viewModel.searchCard(searchQuery)
                            navController.navigate("cardDisplay")
                        },
                        modifier = Modifier.weight(1f).height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF121212),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Search")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            navController.navigate("savedCards")
                        },
                        modifier = Modifier.weight(1f).height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF121212),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Saved Cards")
                    }
                }
            }
        }
    )
}
