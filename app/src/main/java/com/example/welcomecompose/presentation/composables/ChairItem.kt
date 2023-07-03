package com.example.welcomecompose.presentation.composables

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.ui_models.ChairState
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.White87

@Composable
fun ChairItem(
    chairState: ChairState,
    modifier: Modifier = Modifier,
    ocClickChair: (ChairState) -> Unit,
) {
    val tintColor = when (chairState) {
        ChairState.Available -> White87
        ChairState.Taken -> DarkGray
        ChairState.Selected -> PrimaryLight
    }

    val animatedTintColor by animateColorAsState(
        targetValue = tintColor,
        animationSpec = tween(200, easing = FastOutSlowInEasing)
    )

    Crossfade(
        targetState = chairState,
        animationSpec = tween(200, easing = FastOutSlowInEasing),
    ) { state ->
        IconButton(
            onClick = { ocClickChair(state) },
            modifier = modifier,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.chair),
                contentDescription = null,
                tint = animatedTintColor,
                modifier = modifier,
            )
        }
    }
}

@Preview
@Composable
fun ChairItemPreview() {
    ChairItem(chairState = ChairState.Selected, modifier = Modifier.size(75.dp)){}
}