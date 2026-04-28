package com.example.lb8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lb8.ui.theme.LB8Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LB8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessNumberApp()
                }
            }
        }
    }
}

@Composable
fun GuessNumberApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_menu") {
        composable("main_menu") { MainMenu(navController) }
        composable("human_guesses") { HumanGuessesScreen(navController) }
        composable("app_guesses") { AppGuessesScreen(navController) }
        composable("calculator") { CalculatorScreen() }
    }
}

@Composable
fun MainMenu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Гра «Вгадай число»", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Button(
            onClick = { navController.navigate("human_guesses") },
            modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
        ) {
            Text("Я вгадую число")
        }
        Button(
            onClick = { navController.navigate("app_guesses") },
            modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
        ) {
            Text("Андроїд вгадує число")
        }
        Button(onClick = { navController.navigate("calculator") }) {
            Text("Калькулятор")
        }
    }
}

@Composable
fun HumanGuessesScreen(navController: NavController) {
    var secretNumber by remember { mutableIntStateOf(Random.nextInt(1, 101)) }
    var userGuess by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Спробуй вгадати число від 1 до 100") }
    var attempts by remember { mutableIntStateOf(0) }
    var gameOver by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Вгадай число", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
        Spacer(modifier = Modifier.height(16.dp))

        if (!gameOver) {
            OutlinedTextField(
                value = userGuess,
                onValueChange = { if (it.all { char -> char.isDigit() }) userGuess = it },
                label = { Text("Твоє число") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val guess = userGuess.toIntOrNull()
                if (guess == null) {
                    message = "Будь ласка, введи коректне число"
                } else {
                    attempts++
                    if (guess < secretNumber) {
                        message = "Більше!"
                    } else if (guess > secretNumber) {
                        message = "Менше!"
                    } else {
                        message = "Вітаю! Ти вгадав за $attempts спроб!"
                        gameOver = true
                    }
                }
                userGuess = ""
            }) {
                Text("Перевірити")
            }
        } else {
            Button(onClick = {
                secretNumber = Random.nextInt(1, 101)
                attempts = 0
                gameOver = false
                message = "Нова гра! Спробуй вгадати число від 1 до 100"
            }) {
                Text("Грати знову")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.popBackStack() }) {
            Text("Назад в меню")
        }
    }
}

@Composable
fun AppGuessesScreen(navController: NavController) {
    var minRange by remember { mutableIntStateOf(1) }
    var maxRange by remember { mutableIntStateOf(100) }
    var appGuess by remember { mutableIntStateOf(50) }
    var attempts by remember { mutableIntStateOf(1) }
    var gameOver by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Загадай число від 1 до 100", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        if (!gameOver) {
            Text(text = "Мій варіант: $appGuess", fontSize = 24.sp)
            Text(text = "Це спроба №$attempts")
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    if (appGuess > minRange) {
                        maxRange = appGuess - 1
                        appGuess = (minRange + maxRange) / 2
                        attempts++
                    }
                }) {
                    Text("Менше")
                }
                Button(onClick = {
                    gameOver = true
                }) {
                    Text("Вгадав!")
                }
                Button(onClick = {
                    if (appGuess < maxRange) {
                        minRange = appGuess + 1
                        appGuess = (minRange + maxRange) / 2
                        attempts++
                    }
                }) {
                    Text("Більше")
                }
            }
        } else {
            Text(text = "Ура! Я вгадав число $appGuess за $attempts спроб!", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                minRange = 1
                maxRange = 100
                appGuess = 50
                attempts = 1
                gameOver = false
            }) {
                Text("Грати знову")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        TextButton(onClick = { navController.popBackStack() }) {
            Text("Назад в меню")
        }
    }
}
