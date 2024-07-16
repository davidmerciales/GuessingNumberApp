package com.example.myapplication.presentation.ui.screens.insert_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun InsertScreen(
    //navController: NavController
) {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("0") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 20.dp),
                text = "Guess the Number",
                style = TextStyle(
                    fontSize = 35.sp,
                    color = Color(0xFF868686)
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "???",
                    style = TextStyle(
                        fontSize = 35.sp,
                        color = Color(0xFF868686)
                    )
                )
            }


            Spacer(modifier = Modifier.height(50.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.88f),
                value = inputText,
                onValueChange = { inputText = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle().copy(textAlign = TextAlign.Center),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp)
                    .background(Color(0xFF868686), RoundedCornerShape(3.dp))
                    .clickable {
                        if (inputText.isEmpty()) return@clickable

                        try {
                            val textToInt = inputText.toInt()

                            if (textToInt in 0..100) {
                                //navController.navigate(Destinations.ShowScreen(textToInt))
                            } else {
                                Toast.makeText(
                                    context,
                                    "The number should be between 0 and 100",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } catch (e: NumberFormatException) {
                            Toast.makeText(context, "Invalid number format", Toast.LENGTH_LONG)
                                .show()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "OK",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W700
                    )
                )
            }
        }

    }
}