package com.example.welcomecompose.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.LightGray

@Composable
fun HourChip(
    hour: String,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    PrimaryChip(
        text = hour,
        isSelected = isSelected,
        unSelectedTextColor = Black87,
        backgroundColors = listOf(DarkGray, LightGray),
        borderColor = Black8,
        modifier = modifier
    )
}