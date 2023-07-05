package com.example.welcomecompose.presentation.screens.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp

fun Modifier.clickableIf(condition: () -> Boolean, onClick: (() -> Unit)? = null): Modifier {
    return if (condition()) {
        then(Modifier.clickable { onClick?.invoke() })
    } else this
}

fun Modifier.cinemaStyle(scaleRatio: Float = 0.8f): Modifier {
    return then(
        Modifier
            .drawWithContent {
                val path = Path().apply {
                    moveTo(0f, size.height * (1 - scaleRatio))
                    lineTo(0f, size.height)
                    quadraticBezierTo(
                        size.width / 2,
                        size.height * scaleRatio,
                        size.width,
                        size.height
                    )
                    lineTo(size.width, size.height * (1 - scaleRatio))
                    quadraticBezierTo(
                        size.width / 2,
                        0f,
                        0f,
                        size.height - size.height * scaleRatio,
                    )
                }
                clipPath(path = path) {
                    this@drawWithContent.drawContent()
                }
            }
            .graphicsLayer { rotationX = -30f }
    )
}

@Composable
fun ColumnScope.Space(space: Dp) {
    Spacer(modifier = Modifier.height(space))
}

@Composable
fun RowScope.Space(space: Dp) {
    Spacer(modifier = Modifier.width(space))
}

@Composable
fun RowScope.WeightedSpacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun ColumnScope.WeightedSpacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

