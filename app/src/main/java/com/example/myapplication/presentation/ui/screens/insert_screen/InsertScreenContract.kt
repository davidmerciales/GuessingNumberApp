package com.example.myapplication.presentation.ui.screens.insert_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.presentation.navigation.Destinations

class InsertScreenContract {

    sealed interface InsertScreenEvent{
        data class OnButtonOkClick(val input: String, val navHostController: NavController): InsertScreenEvent
    }

    interface InsertScreenState{
        var generatedNumber: Int
        var message: String
        var inputNumber: String
        var isInvalid: Boolean
        var isShowDialog: Boolean
    }

    class MutableInsertScreenState: InsertScreenState{
        override var generatedNumber: Int by mutableIntStateOf(0)
        override var message: String by mutableStateOf("")
        override var inputNumber: String by mutableStateOf("")
        override var isInvalid: Boolean by mutableStateOf(false)
        override var isShowDialog: Boolean by mutableStateOf(false)
    }
}