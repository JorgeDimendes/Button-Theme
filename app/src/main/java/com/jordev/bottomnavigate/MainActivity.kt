package com.jordev.bottomnavigate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jordev.bottomnavigate.navbottom.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark( android.graphics.Color.TRANSPARENT )
        )
        setContent {
            MainScreen()
//            HomeSp()
        }
    }
}