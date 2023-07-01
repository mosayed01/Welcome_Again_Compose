package com.example.welcomecompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
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
fun PrimaryButton(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp),
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
        )
        text?.let {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = it,
                fontSize = 16.sp,
                fontFamily = Sans,
                fontWeight = FontWeight.Normal
            )
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
        PrimaryButton(
            painter = painterResource(id = R.drawable.card),
            onClick = { /*TODO*/ },
            text = "Booking",
            modifier = Modifier.wrapContentWidth(),
        )
    }
}