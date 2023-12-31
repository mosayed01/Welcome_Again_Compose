package com.example.welcomecompose.presentation.screens.buy_tickets.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.welcomecompose.presentation.composables.Chip
import com.example.welcomecompose.presentation.screens.buy_tickets.Day
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.LightGray
import com.example.welcomecompose.presentation.ui.theme.Sans

@Composable
fun DateChip(
    day: Day,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    doWhenSelect: (Day) -> Unit
) {
    Chip(
        text = day.dayNumber.toString(),
        isSelected = isSelected,
        unSelectedTextColor = Black87,
        backgroundColors = listOf(DarkGray, LightGray),
        borderColor = Black8,
        modifier = modifier.padding(horizontal = 8.dp),
        doWhenClick = {
            doWhenSelect(day)
        }
    ) {
        Text(
            text = day.dayName,
            color = it.copy(0.38f),
            fontFamily = Sans,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp
        )
    }
}