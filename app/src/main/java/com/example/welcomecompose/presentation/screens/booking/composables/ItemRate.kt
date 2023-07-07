package com.example.welcomecompose.presentation.screens.booking.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.welcomecompose.presentation.ui.theme.Black38
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.Typography

@Composable
fun ItemRate(
    text: String,
    type: String,
    modifier: Modifier = Modifier,
    secondText: String? = null,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Black87)) {
                    append(text)
                }
                secondText?.let {
                    withStyle(SpanStyle(color = Black38)) {
                        append(it)
                    }
                }
            },
            style = Typography.bodyLarge
        )
        Text(
            text = type,
            style = Typography.labelSmall
        )
    }
}