package com.example.myapplication.presentation.ui.screens.insert_screen

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.presentation.navigation.Destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class InsertScreenViewModel @Inject constructor(): ViewModel() {
    val state: InsertScreenContract.InsertScreenState = InsertScreenContract.MutableInsertScreenState()


    private val _event = MutableSharedFlow<InsertScreenContract.InsertScreenEvent>()
    private val event: SharedFlow<InsertScreenContract.InsertScreenEvent> = _event
    init {
        generateNumber()

        viewModelScope.launch {
            event.collectLatest {event->
                when(event){
                    is InsertScreenContract.InsertScreenEvent.OnButtonOkClick->{
                        checkInputtedNumber(event.input, event.navHostController)
                    }
                }
            }
        }
    }

    private fun checkInputtedNumber(inputtedNumber: String, navController: NavController) {
        state.isInvalid = false
        try {
            val convertedInput = inputtedNumber.toInt()
            val message = when {
                convertedInput < state.generatedNumber -> "The number is lower than the unknown number"
                convertedInput > state.generatedNumber -> "The number is greater than the unknown number"
                else -> {
                    generateNumber()
                    "Congratulations you are correct!"
                }
            }
            state.message = message
            state.isShowDialog = true
            //navController.navigate(Destinations.ShowScreen(convertedInput, state.message))
        } catch (e: NumberFormatException) {
            state.message = "Invalid number format"
            state.isInvalid = true
            state.isShowDialog = true
        }
    }

    private fun generateNumber() {
        state.generatedNumber = Random.nextInt(0,101)
    }
    fun setEvent(event: InsertScreenContract.InsertScreenEvent){
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}