package com.example.welcomecompose.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.BlurredCard
import com.example.welcomecompose.presentation.composables.CircleImage
import com.example.welcomecompose.presentation.composables.PrimaryButton
import com.example.welcomecompose.presentation.composables.PrimaryChip
import com.example.welcomecompose.presentation.ui.theme.Black38
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.LightGray
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans
import com.example.welcomecompose.presentation.ui.theme.White38
import com.example.welcomecompose.presentation.ui.theme.White60
import kotlin.math.roundToInt

@Composable
fun BookingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_2),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Header()
            Spacer(modifier = Modifier.height(125.dp)) // ! Bad practice
            StartButton()
        }

        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .fillMaxWidth()
                .fillMaxHeight(0.53f)
                .align(Alignment.BottomCenter),
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    ItemRate(rateOfTen = 6.8f, type = "IMDp")

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "63%",
                            fontFamily = Sans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Rotten Tomatoes",
                            color = Black38,
                            fontFamily = Sans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }

                    ItemRate(rateOfTen = 4f, type = "IGN")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Fantastic Beasts: The \nSecrets of Dumbledore",
                    color = Black87,
                    fontSize = 16.sp,
                    fontFamily = Sans,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    PrimaryChip(
                        text = "Fantasy",
                        isEnabled = false,
                        unSelectedTextColor = Black87,
                        backgroundColors = listOf(DarkGray, LightGray),
                        borderColor = Black8,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    PrimaryChip(
                        text = "Adventure",
                        isEnabled = false,
                        unSelectedTextColor = Black87,
                        backgroundColors = listOf(DarkGray, LightGray),
                        borderColor = Black8,
                        fontSize = 12.sp
                    )
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(count = 10) {
                        CircleImage(
                            painter = painterResource(id = R.drawable.img_2),
                            onClick = { /*TODO*/ })
                    }
                }
                Text(
                    text = "Professor Albus Dumbledore knows the powerful," +
                            "dark wizard Gellert Grindelwald is moving to seize" +
                            "control of the wizarding world. Unable to stop him...",
                    color = Black87,
                    fontSize = 16.sp,
                    fontFamily = Sans,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                PrimaryButton(
                    painter = painterResource(id = R.drawable.card),
                    onClick = { /*TODO*/ },
                    text = "Booking",
                    modifier = Modifier.height(56.dp),
                )
            }
        }
    }
}

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

@Composable
fun StartButton() {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(56.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(PrimaryLight)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_poly),
                contentDescription = "",
                tint = OnPrimaryLight,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
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


        BlurredCard(onClick = { /*TODO*/ }, modifier = Modifier) {
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.time),
                    contentDescription = "date",
                    tint = White38,
                    modifier = Modifier.size(16.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "2h 23m",
                    fontSize = 12.sp,
                    fontFamily = Sans,
                    fontWeight = FontWeight.Normal,
                    color = White60
                )
            }
        }
    }
}


@Preview
@Composable
fun BookingScreenPreview() {
    BookingScreen()
}