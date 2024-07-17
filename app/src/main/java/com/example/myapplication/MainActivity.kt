package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.presentation.navigation.Destinations
import com.example.myapplication.presentation.theme.MyApplicationTheme
import com.example.myapplication.presentation.ui.screens.insert_screen.InsertScreen
import com.example.myapplication.presentation.ui.screens.show_screen.ShowScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
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
                            //InsertScreen(navController)
                            InsertScreen(navController)
                        }

                        composable<Destinations.ShowScreen> { navBackStackEntry ->
                            val arguments = navBackStackEntry.toRoute<Destinations.ShowScreen>()
                            ShowScreen(navController, arguments.data, arguments.message)
                        }
                    }
                }
            }
        }
    }
}


