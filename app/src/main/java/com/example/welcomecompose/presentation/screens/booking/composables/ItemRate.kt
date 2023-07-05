package com.example.welcomecompose.presentation.screens.booking.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.welcomecompose.presentation.ui.theme.Black38
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.Sans
import kotlin.math.roundToInt

@Composable
fun ItemRate(
    rateOfTen: Float,
    type: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Black87)) {
                    append(((rateOfTen * 10.0).roundToInt() / 10.0).toString())
                }
                withStyle(SpanStyle(color = Black38)) {
                    append("/10")
                }
            },
            fontFamily = Sans,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Text(
            text = type,
            color = Black38,
            fontFamily = Sans,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }

}