package com.example.welcomecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.welcomecompose.presentation.screens.HomeScreen
import com.example.welcomecompose.presentation.ui.theme.WelcomeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeComposeTheme {
                HomeScreen()
            }
        }
    }
}

