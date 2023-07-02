package com.example.welcomecompose.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.components.BlurredCard
import com.example.welcomecompose.presentation.composables.components.DateChip
import com.example.welcomecompose.presentation.composables.components.Day
import com.example.welcomecompose.presentation.composables.components.HourChip
import com.example.welcomecompose.presentation.composables.components.PrimaryButton
import com.example.welcomecompose.presentation.ui.theme.Black60
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans
import com.example.welcomecompose.presentation.ui.theme.White60
import com.example.welcomecompose.presentation.ui.theme.White87

@Composable
fun BuyTicketsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
        ) {
            BlurredCard(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            1.5.dp,
                            color = OnPrimaryLight,
                            shape = CircleShape
                        )
                        .size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Close",
                        tint = OnPrimaryLight,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                modifier = Modifier
                    .graphicsLayer { rotationX = -50f }
            )

            Image(
                painter = painterResource(id = R.drawable.img_5),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                SelectedRadioItem(state = State.Available)
                SelectedRadioItem(state = State.Taken)
                SelectedRadioItem(state = State.Selected)
            }
        }
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter),
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                val days = listOf(
                    Day(14, "Thu"),
                    Day(15, "Fri"),
                    Day(16, "Sat"),
                    Day(17, "Sun"),
                    Day(18, "Mon"),
                    Day(19, "Tue"),
                    Day(20, "Tue"),
                    Day(21, "Tue"),
                    Day(22, "Tue"),
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(days) { DateChip(it, isSelected = it.dayNumber == 17) }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val timeList = listOf(
                    "10:00",
                    "12:30",
                    "15:30",
                    "18:00",
                    "18:30",
                    "19:00",
                    "20:00",
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(timeList) {
                        HourChip(it, isSelected = it == "10:00")
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "$100.00",
                            fontSize = 24.sp,
                            fontFamily = Sans,
                            fontWeight = FontWeight.Bold,
                            color = Black87,
                            )
                        Text(
                            text = "4 tickets",
                            fontSize = 11.sp,
                            fontFamily = Sans,
                            fontWeight = FontWeight.Normal,
                            color = Black60
                            )

                    }
                    PrimaryButton(
                        painter = painterResource(id = R.drawable.card),
                        onClick = { /*TODO*/ },
                        text = "Buy Tickets",
                        modifier = Modifier.height(56.dp),
                    )

                }

            }
        }
    }
}


@Composable
fun SelectedRadioItem(
    state: State
) {
    val name = when (state) {
        State.Available -> "Available"
        State.Taken -> "Taken"
        State.Selected -> "Selected"
    }
    val color = when (state) {
        State.Available -> White87
        State.Taken -> DarkGray
        State.Selected -> PrimaryLight
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            fontFamily = Sans,
            fontWeight = FontWeight.Normal,
            color = White60,
            fontSize = 14.sp
        )
    }

}

enum class State {
    Available,
    Taken,
    Selected
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BuyTicketsScreenPrev() {
    BuyTicketsScreen()
}