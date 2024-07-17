package com.example.myapplication.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destinations {

    @Serializable
    data object InsertScreen : Destinations()
}