package com.jordev.bottomnavigate.ui.theme

import androidx.activity.SystemBarStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val lightTheme = lightColorScheme(
    primary = LIGHT_PURPLE, //Cor do botão
    onPrimary = WHITE, //Muda cor de texto botão
    secondary = RED, //Muda cor de texto
    background = WHITE, //Muda cor de fundo
    surface = WHITE, // Cor para cartões e AppBar
)

private val darkTheme = darkColorScheme(
    primary = DARK_PURPLE, //Cor do botão
    onPrimary = WHITE, //Muda cor de texto botão
    secondary = WHITE, //Muda cor de texto
    background = BLACK900, //Muda cor de fundo
    //surface = BLACK900, // Cor para cartões e AppBar
)

private val foresTheme = lightColorScheme(
    primary = VERDE, //Cor do botão
    onPrimary = WHITE, //Muda cor de texto botão
    secondary = WHITE, //Muda cor de texto
    background = RED, //Muda cor de fundo
)

@Composable
fun MyTheme(
    theme: String,
    content: @Composable () -> Unit
){
    val systemUiController = rememberSystemUiController()

    val colorSchem = when{
        theme ==  "1" -> {
            lightTheme
        }
    theme == "2" -> {
        darkTheme
    }else -> {
            foresTheme
        }
    }
    systemUiController.setStatusBarColor(
        color = Color.Transparent, // Cor de fundo da Status Bar
        darkIcons = theme == "1" // Ícones escuros no tema claro, brancos no escuro
    )

    MaterialTheme(
        colorScheme = colorSchem,
        typography = Typography,
        content = content
    )
}