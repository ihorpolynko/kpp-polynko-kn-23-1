package com.example.lb8

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen() {

    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var displayText by remember { mutableStateOf("0") }

    val buttons = listOf(
        "C", "←", "+/-", "/",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "x²", "="
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_black))
            .padding(16.dp)
    ) {

        // Дисплей
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = displayText,
                fontSize = when {
                    displayText.length > 12 -> 32.sp
                    displayText.length > 9 -> 42.sp
                    else -> 56.sp
                },
                color = Color.White,
                textAlign = TextAlign.End,
                maxLines = 1
            )
        }

        // Кнопки
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.height(500.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(buttons) { btn ->

                CalculatorButton(symbol = btn) {

                    when (btn) {

                        "C" -> {
                            firstNumber = ""
                            secondNumber = ""
                            operation = ""
                            displayText = "0"
                        }

                        "←" -> {
                            if (displayText.isNotEmpty()) {
                                displayText = displayText.dropLast(1)

                                if (displayText.isEmpty()) {
                                    displayText = "0"
                                }
                            }
                        }

                        "+", "-", "x", "/" -> {
                            if (firstNumber.isEmpty()) {
                                firstNumber = displayText
                                operation = btn
                                displayText = "0"
                            }
                        }

                        "=" -> {

                            secondNumber = displayText

                            val num1 = firstNumber.toDoubleOrNull() ?: 0.0
                            val num2 = secondNumber.toDoubleOrNull() ?: 0.0

                            val result = when (operation) {
                                "+" -> num1 + num2
                                "-" -> num1 - num2
                                "x" -> num1 * num2
                                "/" -> {
                                    if (num2 == 0.0) {
                                        displayText = "Error"
                                        return@CalculatorButton
                                    }
                                    num1 / num2
                                }

                                else -> 0.0
                            }

                            displayText =
                                if (result % 1 == 0.0)
                                    result.toInt().toString()
                                else
                                    result.toString()

                            firstNumber = ""
                            secondNumber = ""
                            operation = ""
                        }

                        "x²" -> {
                            val number = displayText.toDoubleOrNull() ?: 0.0
                            val result = number * number

                            displayText =
                                if (result % 1 == 0.0)
                                    result.toInt().toString()
                                else
                                    result.toString()
                        }

                        "+/-" -> {
                            if (displayText != "0") {

                                displayText =
                                    if (displayText.startsWith("-")) {
                                        displayText.drop(1)
                                    } else {
                                        "-$displayText"
                                    }
                            }
                        }

                        "." -> {
                            if (!displayText.contains(".")) {
                                displayText += "."
                            }
                        }

                        else -> {

                            if (displayText.length < 15) {

                                if (displayText == "0") {
                                    displayText = btn
                                } else {
                                    displayText += btn
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    symbol: String,
    onClick: () -> Unit
) {

    val buttonColor = when (symbol) {

        "/", "x", "-", "+", "=" ->
            R.color.button_orange

        "C", "←", "+/-", "x²" ->
            R.color.button_dark_gray

        else ->
            R.color.button_gray
    }

    Button(
        onClick = onClick,
        modifier = Modifier.size(85.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(buttonColor)
        )
    ) {

        Text(
            text = symbol,
            fontSize = 26.sp,
            color = Color.White
        )
    }
}