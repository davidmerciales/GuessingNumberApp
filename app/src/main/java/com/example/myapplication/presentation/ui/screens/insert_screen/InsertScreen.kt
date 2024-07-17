package com.example.myapplication.presentation.ui.screens.insert_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.presentation.navigation.Destinations
import com.example.myapplication.presentation.ui.screens.common.BasicAlertDialog

@Preview
@Composable
fun InsertScreen(
    navController: NavController,
    viewModel: InsertScreenViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (viewModel.state.isShowDialog) {
            BoxWithConstraints(modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
                .background(Color.Black.copy(alpha = .7f))
                .align(Alignment.Center)
                .clickable { }) {
                val minDimension = minOf(maxWidth, maxHeight)

                BasicAlertDialog(
                    modifier = Modifier
                        .zIndex(1f)
                        .height(minDimension * 0.45f)
                        .width(minDimension * 0.8f),
                    title = "Your guess is: ${viewModel.state.inputNumber}",
                    message = viewModel.state.message,
                    positiveButtonText = "OK",
                    positiveClick = {
                        viewModel.state.isShowDialog = false
                    }
                )
            }
        }

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
                value = viewModel.state.inputNumber,
                onValueChange = { viewModel.state.inputNumber = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                leadingIcon = {},
                trailingIcon = {
                    if (viewModel.state.isInvalid) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Error",
                            tint = Color.Red
                        )
                    }
                },
                isError = viewModel.state.isInvalid,
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
                        if (viewModel.state.inputNumber.isEmpty()) return@clickable

                        viewModel.setEvent(
                            InsertScreenContract.InsertScreenEvent.OnButtonOkClick(
                                viewModel.state.inputNumber,
                                navController
                            )
                        )
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