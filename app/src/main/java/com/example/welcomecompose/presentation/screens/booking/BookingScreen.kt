package com.example.welcomecompose.presentation.screens.booking

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.BottomSheet
import com.example.welcomecompose.presentation.composables.Chip
import com.example.welcomecompose.presentation.composables.CircleImage
import com.example.welcomecompose.presentation.composables.TicketsButton
import com.example.welcomecompose.presentation.screens.booking.composables.BookingAppbar
import com.example.welcomecompose.presentation.screens.booking.composables.ItemRate
import com.example.welcomecompose.presentation.screens.booking.composables.PlayButton
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.Typography
import kotlin.math.roundToInt

@Composable
fun BookingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by remember { mutableStateOf(BookingUiState()) }
    BookingScreenContent(
        state = state,
        modifier = modifier,
    ) { navController.popBackStack() }
}

@Composable
fun BookingScreenContent(
    state: BookingUiState,
    modifier: Modifier = Modifier,
    onClickExit: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.img_1),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BookingAppbar(state.time, onClickExit = onClickExit)
        PlayButton()
        BottomSheet(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            BottomSheetContent(state)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BottomSheetContent(
    state: BookingUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        ItemRate(
            text = ((state.rateOfTenIMDp * 10.0).roundToInt() / 10.0).toString(),
            secondText = "/10",
            type = "IMDp"
        )

        ItemRate(text = "${state.rottenTomatoes}%", type = "Rotten Tomatoes")

        ItemRate(
            text = ((state.rateOfTenIGN * 10.0).roundToInt() / 10.0).toString(),
            secondText = "/10",
            type = "IGN"
        )
    }

    Text(
        text = state.title,
        style = Typography.bodyLarge,
        modifier = Modifier.padding(top = 16.dp)
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(top = 8.dp)
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

    TicketsButton(
        painter = painterResource(id = R.drawable.card),
        onClick = { /*TODO*/ },
        text = "Booking",
        modifier = Modifier.padding(top = 24.dp).height(56.dp),
    )
}

@Preview
@Composable
fun Preview() {
    BookingScreenContent(state = BookingUiState()) {}
}