package com.example.welcomecompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.welcomecompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlurredCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(id = R.drawable.img_2),
    content: @Composable () -> Unit,
) {
    Card(
        onClick = { onClick() },
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier.blur(10.dp),
            )
            content()
        }
    }
}

@Preview()
@Composable
fun BlurredCardPreview() {
        BlurredCard(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_poly),
                contentDescription = null
            )
        }
}

