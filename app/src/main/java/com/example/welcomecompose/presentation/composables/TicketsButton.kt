package com.example.welcomecompose.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans

@Composable
fun TicketsButton(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    iconSize: Int = 24,
) {
    var isVisibleText by remember { mutableStateOf(false) }

    Button(
        onClick = {
            isVisibleText = !isVisibleText
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryLight,
            contentColor = OnPrimaryLight,
        ),
        shape = if (text != null) MaterialTheme.shapes.extraLarge else CircleShape
    ) {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = OnPrimaryLight,
            modifier = Modifier.size(iconSize.dp)
        )
        text?.let {
            AnimatedVisibility(visible = isVisibleText) {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    fontFamily = Sans,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(device = "id:pixel", showBackground = true, showSystemUi = true)
@Composable
fun PrimaryButtonPreview() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TicketsButton(
            painter = painterResource(id = R.drawable.card),
            onClick = { /*TODO*/ },
            text = "Booking",
            modifier = Modifier.height(56.dp),
        )
    }
}