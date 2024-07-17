package com.example.myapplication.presentation.ui.screens.show_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ShowScreen(
    navController: NavController,
    data: Int?,
    message: String?
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = message?:"",
            style = TextStyle(
                fontSize = 35.sp,
                color = Color.Black
            )
        )

        Text(text = if (data == -1) "" else data.toString(),
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Black
            ))

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(text = "Back")
        }
    }
}