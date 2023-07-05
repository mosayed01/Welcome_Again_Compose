package com.example.welcomecompose.presentation.screens.buy_tickets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.welcomecompose.presentation.composables.Chip
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.LightGray

@Composable
fun HourChip(
    hour: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    doWhenSelectHour: (String) -> Unit,
) {
    Chip(
        text = hour,
        isSelected = isSelected,
        unSelectedTextColor = Black87,
        backgroundColors = listOf(DarkGray, LightGray),
        borderColor = Black8,
        modifier = modifier,
        doWhenClick = {
            doWhenSelectHour(hour)
        }
    )
}