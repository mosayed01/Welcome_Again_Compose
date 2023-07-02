package com.example.welcomecompose.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.ui.theme.Black38
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.LightGray
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans
import com.example.welcomecompose.presentation.ui.theme.White60

@Composable
fun PrimaryChip(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    borderColor: Color = White60,
    backgroundColors: List<Color> = listOf(PrimaryLight),
    selectedTextColor: Color = OnPrimaryLight,
    unSelectedTextColor: Color = OnPrimaryLight,
    doWhenSelect: () -> Unit = {},
) {
    var isSelectedState by remember { mutableStateOf(isSelected) }

    val actualBackgroundColors = if (isSelectedState) backgroundColors else listOf(Color.Transparent)
    val actualBorderColor = if (isSelectedState) Color.Transparent else borderColor

    LaunchedEffect(key1 = isSelectedState) {
        if (isSelectedState) {
            doWhenSelect()
        }
    }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable { isSelectedState = !isSelectedState }
            .border(border = BorderStroke(1.dp, color = actualBorderColor), shape = CircleShape)
            .drawBehind {
                if (actualBackgroundColors.size < 2) {
                    drawRoundRect(actualBackgroundColors.first())
                }
                else {
                    drawRoundRect(brush = Brush.verticalGradient(actualBackgroundColors))
                }
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontFamily = Sans,
            fontWeight = FontWeight.Normal,
            color = if (isSelectedState) selectedTextColor else unSelectedTextColor
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrimaryChipPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Image(
            painter = painterResource(id = R.drawable.img_2),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .blur(5.dp)
        )
        Row(modifier = Modifier.align(Alignment.TopCenter)) {
            PrimaryChip(text = "Now Showing", isSelected = true)
            PrimaryChip(text = "Coming Soon", isSelected = false)
        }
        PrimaryChip(
            text = "10:00",
            isSelected = true,
            unSelectedTextColor = Black87,
            backgroundColors = listOf(DarkGray, LightGray),
            borderColor = Black38
        )
    }
}