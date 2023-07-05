package com.example.welcomecompose.presentation.screens.home.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

@Composable
fun GradientOverlay(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier
        .fillMaxSize()
        .drawWithContent {
            drawContent()
            drawHalfGradient()
        }) {
        content()
    }
}

private fun ContentDrawScope.drawHalfGradient() {
    drawRect(
        brush = Brush.verticalGradient(
            listOf(
                Color.Transparent,
                Color.Transparent,
                Color.Transparent,
                Color.White.copy(0.1f),
                Color.White.copy(0.2f),
                Color.White.copy(0.3f),
                Color.White.copy(0.4f),
                Color.White.copy(0.5f),
                Color.White.copy(0.7f),
                Color.White.copy(0.8f),
                Color.White.copy(0.9f),
                Color.White,
            ),
        ),
        topLeft = Offset(0f, size.height / 5),
    )
}