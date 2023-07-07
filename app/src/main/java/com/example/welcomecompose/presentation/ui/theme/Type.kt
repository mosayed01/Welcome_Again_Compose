package com.example.welcomecompose.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = Sans,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        color = Black87,
    ),
    bodySmall = TextStyle(
        fontFamily = Sans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    ),
    labelSmall = TextStyle(
        color = Black38,
        fontFamily = Sans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )

)