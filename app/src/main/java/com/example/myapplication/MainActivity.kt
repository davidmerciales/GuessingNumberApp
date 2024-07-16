package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Destinations.InsertScreen
                    ) {

                        composable<Destinations.InsertScreen> {
                            InsertScreen(navController)
                        }

                        composable<Destinations.ShowScreen> { navBackStackEntry ->
                            val arguments = navBackStackEntry.toRoute<Destinations.ShowScreen>()
                            ShowScreen(navController, arguments.data)
                        }
                    }
                }
            }
        }
    }
}

@Serializable
sealed class Destinations {

    @Serializable
    data object InsertScreen : Destinations()

    @Serializable
    data class ShowScreen(val data: Int) : Destinations()
}


@Composable
fun InsertScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("0") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Input number") }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.25f),
                onClick = {
                    if (inputText.isEmpty()) return@Button

                    try {
                        val textToInt = inputText.toInt()

                        if (textToInt in 0..100) {
                            navController.navigate(Destinations.ShowScreen(textToInt))
                        } else {
                            Toast.makeText(context, "The number should be between 0 and 100", Toast.LENGTH_LONG).show()
                        }
                    } catch (e: NumberFormatException) {
                        Toast.makeText(context, "Invalid number format", Toast.LENGTH_LONG).show()
                    }
                }
            ) {
                Text(text = "Ok")
            }
        }

    }
}

@Composable
fun ShowScreen(
    navController: NavController,
    data: Int?
) {

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(text = data.toString())

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