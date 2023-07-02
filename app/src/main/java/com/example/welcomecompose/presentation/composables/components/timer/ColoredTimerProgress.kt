package com.example.welcomecompose.presentation.composables.components.timer

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ColoredTimerProgress(
    maxTime: Int,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 2.dp,
) {
    val scope = rememberCoroutineScope()
    var currentTime by remember {
        mutableStateOf(maxTime)
    }
    val animatedTime by animateFloatAsState(
        targetValue = currentTime.toFloat(),
        animationSpec = TweenSpec(1000, easing = LinearEasing), label = ""
    )
    val color by animateColorAsState(targetValue = if (currentTime < maxTime / 2) Color.Red else Color.Green,
        label = ""
    )

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while (true) {
                if (currentTime == 0) {
                    break
                }
                delay(1000)
                currentTime--
            }
        }
    }

    ShderProgress(
        progress = animatedTime/maxTime,
        progressColor = color,
        modifier = modifier
    ) {
        Text(
            text = "${animatedTime.toInt()}",
            style = MaterialTheme.typography.bodyMedium,
            color = color.copy(alpha = 0.5f)
        )
    }
}

@Preview
@Composable
fun ColoredTimerProgressPreview() {
    ColoredTimerProgress(
        maxTime = 30,
        modifier = Modifier.size(85.dp)
    )
}
