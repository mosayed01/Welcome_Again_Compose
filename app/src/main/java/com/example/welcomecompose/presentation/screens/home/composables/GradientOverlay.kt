package com.example.welcomecompose.presentation.screens.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.welcomecompose.R

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
                Color.White,
                Color.White,
                Color.White,
            ),
        ),
        topLeft = Offset(0f, size.height / 5),
        blendMode = BlendMode.SrcOver,
        size = Size(size.width, size.height * 0.75f)
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    GradientOverlay {
        Image(
            painter = painterResource(id = R.drawable.img_3),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .graphicsLayer {
                    renderEffect = BlurEffect(
                        50f,
                        50f,
                    )
                }
        )
    }
}