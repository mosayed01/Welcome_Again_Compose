package com.example.welcomecompose.presentation.composables.util

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

fun Modifier.clickableIf(condition: () -> Boolean, onClick: (() -> Unit)? = null): Modifier {
    return if (condition()) {
        then(Modifier.clickable { onClick?.invoke() })
    } else this
}