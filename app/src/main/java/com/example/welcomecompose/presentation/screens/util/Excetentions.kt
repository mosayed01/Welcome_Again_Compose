package com.example.welcomecompose.presentation.screens.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.clickableIf(condition: () -> Boolean, onClick: (() -> Unit)? = null): Modifier {
    return if (condition()) {
        then(Modifier.clickable { onClick?.invoke() })
    } else this
}

@Composable
fun ColumnScope.Space(space: Int){
    Spacer(modifier = Modifier.height(space.dp))
}

@Composable
fun RowScope.Space(space: Int){
    Spacer(modifier = Modifier.width(space.dp))
}

@Composable
fun RowScope.WeightedSpacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun ColumnScope.WeightedSpacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

