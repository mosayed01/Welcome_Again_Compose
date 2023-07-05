package com.example.welcomecompose.presentation.screens.booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.BlurredCard
import com.example.welcomecompose.presentation.composables.CircleImage
import com.example.welcomecompose.presentation.composables.TicketsButton
import com.example.welcomecompose.presentation.composables.Chip
import com.example.welcomecompose.presentation.screens.util.Space
import com.example.welcomecompose.presentation.ui.theme.Black38
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans
import com.example.welcomecompose.presentation.ui.theme.White38
import com.example.welcomecompose.presentation.ui.theme.White60
import kotlin.math.roundToInt

@Composable
fun BookingScreen(
    navController: NavController
) {
    val bookingUiState by remember { mutableStateOf(BookingUiState()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Header(bookingUiState.time) {
                navController.popBackStack()
            }
            Space(space = 125.dp) // ! bad practice
            StartButton()
        }
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            MovieContent(bookingUiState)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieContent(
    bookingUiState: BookingUiState
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
            ItemRate(rateOfTen = bookingUiState.rateOfTenIMDp, type = "IMDp")

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${bookingUiState.rottenTomatoes}%",
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

            ItemRate(rateOfTen = bookingUiState.rateOfTenIGN, type = "IGN")
        }
        Space(space = 16.dp)
        Text(
            text = bookingUiState.title,
            color = Black87,
            fontSize = 16.sp,
            fontFamily = Sans,
            fontWeight = FontWeight.Medium
        )
        Space(space = 8.dp)
        FlowRow(
            horizontalArrangement = Arrangement.Center,
        ) {
            bookingUiState.genres.forEach {
                Chip(
                    text = it,
                    isEnabled = false,
                    unSelectedTextColor = Black87,
                    borderColor = Black8,
                    fontSize = 12.sp
                )
                Space(space = 4.dp)
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(bookingUiState.images) {
                CircleImage(
                    painter = painterResource(id = it),
                    onClick = { /*TODO*/ }
                )
                Space(space = 4.dp)
            }
        }
        Text(
            text = bookingUiState.overview,
            color = Black87,
            fontSize = 16.sp,
            fontFamily = Sans,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Space(space = 32.dp)
        TicketsButton(
            painter = painterResource(id = R.drawable.card),
            onClick = { /*TODO*/ },
            text = "Booking",
            modifier = Modifier.height(56.dp),
        )
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
fun Header(
    time: String,
    onClickExit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BlurredCard(
            onClick = onClickExit,
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
                Space(space = 4.dp)
                Text(
                    text = time,
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
    BookingScreen(rememberNavController())
}