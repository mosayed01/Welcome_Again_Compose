package com.example.welcomecompose.presentation.composables.timer

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShderProgress(
    progress: Float,
    progressColor: Color,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 2.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        /// region circle
        Canvas(modifier = modifier) {
            val size = minOf(size.width, size.height)
            val center = Offset(size / 2f, size / 2f)
            val radius = (size - strokeWidth.toPx()) / 2f

            drawCircle(
                color = progressColor.copy(alpha = 0.01f),
                radius = radius,
                center = center,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )

            val sweepAngle = 360f * progress
            drawArc(
                brush = Brush.linearGradient(
                    listOf(
                        progressColor.copy(alpha = 0.0f),
                        progressColor.copy(alpha = 0.2f),
                        progressColor.copy(alpha = 0.8f),
                        progressColor.copy(alpha = 0.85f),
                        progressColor.copy(alpha = 0.8f),
                        progressColor.copy(alpha = 0.2f),
                        progressColor.copy(alpha = 0.0f),
                    )
                ),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        /// endregion

        /// region content
        content(this)
        /// endregion
    }
}


@Preview(
    device = "id:pixel_xl",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)
@Composable
fun TimerProgressPreview() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        ShderProgress(
            progress = 1f,
            progressColor = Color.Blue,
            modifier = Modifier.size(50.dp)
        ) {
            Text(
                text = "15",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Blue.copy(alpha = 0.87f)
            )
        }
    }
}