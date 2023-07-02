package com.example.welcomecompose.presentation.composables.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.LightGray
import com.example.welcomecompose.presentation.ui.theme.Sans


data class Day(val dayNumber: Int, val dayName: String)

@Composable
fun DateChip(
    day: Day,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    PrimaryChip(
        text = day.dayNumber.toString(),
        isSelected = isSelected,
        unSelectedTextColor = Black87,
        backgroundColors = listOf(DarkGray, LightGray),
        borderColor = Black8,
        modifier = modifier.padding(horizontal = 8.dp)
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