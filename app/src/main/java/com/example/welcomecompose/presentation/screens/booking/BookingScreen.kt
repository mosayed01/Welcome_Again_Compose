package com.example.welcomecompose.presentation.screens.booking

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.BottomSheet
import com.example.welcomecompose.presentation.composables.Chip
import com.example.welcomecompose.presentation.composables.CircleImage
import com.example.welcomecompose.presentation.composables.TicketsButton
import com.example.welcomecompose.presentation.screens.booking.composables.Header
import com.example.welcomecompose.presentation.screens.booking.composables.ItemRate
import com.example.welcomecompose.presentation.screens.booking.composables.PlayButton
import com.example.welcomecompose.presentation.screens.util.Space
import com.example.welcomecompose.presentation.ui.theme.Black38
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.Sans
import com.example.welcomecompose.presentation.ui.theme.Typography

@Composable
fun BookingScreen(
    navController: NavController,
    screenPadding: PaddingValues
) {
    val state by remember { mutableStateOf(BookingUiState()) }
    BookingScreenContent(state = state, screenPadding = screenPadding) { navController.popBackStack() }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BookingScreenContent(
    state: BookingUiState,
    screenPadding: PaddingValues,
    onClickExit: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize().padding(screenPadding)) {
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
            Header(state.time, onClickExit = onClickExit)
            Space(space = 125.dp) // ! bad practice
            PlayButton()
        }
        BottomSheet(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                ItemRate(rateOfTen = state.rateOfTenIMDp, type = "IMDp")

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${state.rottenTomatoes}%",
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

                ItemRate(rateOfTen = state.rateOfTenIGN, type = "IGN")
            }
            Space(space = 16.dp)
            Text(
                text = state.title,
                style = Typography.bodyLarge
            )
            Space(space = 8.dp)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                state.genres.forEach {
                    Chip(
                        text = it,
                        isEnabled = false,
                        unSelectedTextColor = Black87,
                        borderColor = Black8,
                        fontSize = 12.sp
                    )
                }
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.images) {
                    CircleImage(
                        painter = painterResource(id = it),
                        onClick = { /*TODO*/ }
                    )
                }
            }
            Text(
                text = state.overview,
                color = Black87,
                style = Typography.bodyLarge,
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
}