package com.example.welcomecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.welcomecompose.presentation.components.timer.TimerProgress
import com.example.welcomecompose.presentation.ui.theme.WelcomeComposeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeComposeTheme {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    var countDownTimer by remember { mutableStateOf(30) }

                    LaunchedEffect(key1 = Unit){
                        while (true){
                            if (countDownTimer == 0){
                                break
                            }
                            delay(1000)
                            countDownTimer--
                        }
                    }

                    TimerProgress(
                        maxTime = 30,
                        currentTime = countDownTimer,
                        progressColor = Color.Blue,
                        modifier = Modifier.size(50.dp)
                    ) {
                        Text(
                            text = "$countDownTimer",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Blue.copy(alpha = 0.87f)
                        )
                    }
                }
            }
        }
    }
}

