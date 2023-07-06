package com.example.welcomecompose.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.screens.util.clickableIf
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans
import com.example.welcomecompose.presentation.ui.theme.White60

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isEnabled: Boolean = true,
    fontSize: TextUnit = TextUnit.Unspecified,
    borderColor: Color = White60,
    backgroundColors: List<Color> = listOf(PrimaryLight),
    selectedTextColor: Color = OnPrimaryLight,
    unSelectedTextColor: Color = OnPrimaryLight,
    doWhenClick: () -> Unit = {},
    moreContent: (@Composable ColumnScope.(Color) -> Unit)? = null
) {
    val actualBackgroundColors =
        if (isSelected) backgroundColors else listOf(Color.Transparent)
    val actualBorderColor = if (isSelected) Color.Transparent else borderColor
    val shape = if (moreContent == null) CircleShape else RoundedCornerShape(40)

    Box(
        modifier = Modifier
            .clip(shape = shape)
            .clickableIf(condition = { isEnabled }) {
                doWhenClick()
            }
            .border(border = BorderStroke(1.dp, color = actualBorderColor), shape = shape)
            .drawBehind {
                if (actualBackgroundColors.size < 2) {
                    drawRoundRect(actualBackgroundColors.first())
                } else {
                    drawRoundRect(brush = Brush.verticalGradient(actualBackgroundColors))
                }
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textColor = if (isSelected) selectedTextColor else unSelectedTextColor
            Text(
                text = text,
                fontFamily = Sans,
                fontWeight = FontWeight.Normal,
                color = textColor,
                fontSize = fontSize
            )
            if (moreContent != null) {
                moreContent(this, textColor)
            }
        }
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
            Chip(text = "Now Showing", isSelected = true, isEnabled = true)
            Chip(text = "Coming Soon", isSelected = false, isEnabled = false)
        }
    }
}