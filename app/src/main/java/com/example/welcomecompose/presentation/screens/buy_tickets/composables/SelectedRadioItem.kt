package com.example.welcomecompose.presentation.screens.buy_tickets.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.welcomecompose.presentation.screens.util.Space
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans
import com.example.welcomecompose.presentation.ui.theme.White60
import com.example.welcomecompose.presentation.ui.theme.White87

@Composable
fun SelectedRadioItem(
    chairState: ChairState
) {
    val name = when (chairState) {
        ChairState.Available -> "Available"
        ChairState.Taken -> "Taken"
        ChairState.Selected -> "Selected"
    }
    val color = when (chairState) {
        ChairState.Available -> White87
        ChairState.Taken -> DarkGray
        ChairState.Selected -> PrimaryLight
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Space(space = 8.dp)
        Text(
            text = name,
            fontFamily = Sans,
            fontWeight = FontWeight.Normal,
            color = White60,
            fontSize = 14.sp
        )
    }
}