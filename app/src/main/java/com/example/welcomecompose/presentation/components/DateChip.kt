package com.example.welcomecompose.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.LightGray
import com.example.welcomecompose.presentation.ui.theme.Sans

@Composable
fun DateChip(
    dayNumber: Int,
    dayName: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    PrimaryChip(
        text = dayNumber.toString(),
        isSelected = isSelected,
        unSelectedTextColor = Black87,
        backgroundColors = listOf(DarkGray, LightGray),
        borderColor = Black8,
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Text(
            text = dayName,
            color = it.copy(0.6f),
            fontFamily = Sans,
            fontWeight = FontWeight.Normal
        )
    }
}