package com.example.welcomecompose.presentation.screens.buy_tickets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.BlurredCard
import com.example.welcomecompose.presentation.composables.PrimaryButton
import com.example.welcomecompose.presentation.composables.ui_models.ChairState
import com.example.welcomecompose.presentation.screens.buy_tickets.composables.DateChip
import com.example.welcomecompose.presentation.screens.buy_tickets.composables.HourChip
import com.example.welcomecompose.presentation.screens.buy_tickets.composables.RowOfPairOfChairs
import com.example.welcomecompose.presentation.screens.util.Space
import com.example.welcomecompose.presentation.screens.util.cinemaStyle
import com.example.welcomecompose.presentation.ui.theme.Black60
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans
import com.example.welcomecompose.presentation.ui.theme.White60
import com.example.welcomecompose.presentation.ui.theme.White87

@Composable
fun BuyTicketsScreen(navController: NavController) {
    var buyTicketsUiState by remember { mutableStateOf(BuyTicketsUiState()) }
    BuyTicketsContent(
        onClickExit = { navController.popBackStack() },
        onClickBuy = {

        },
        state = buyTicketsUiState,
        doWhenSelectDay = {
            buyTicketsUiState = buyTicketsUiState.copy(selectedDay = it)
        },
        doWhenSelectHour = {
            buyTicketsUiState = buyTicketsUiState.copy(selectedTime = it)
        }
    )
}

@Composable
fun BuyTicketsContent(
    onClickExit: () -> Unit,
    onClickBuy: () -> Unit,
    doWhenSelectDay: (Day) -> Unit,
    doWhenSelectHour: (String) -> Unit,
    state: BuyTicketsUiState,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val (xIcon, image, rowChairs1,
            rowChairs2, rowChairs3, rowChairs4,
            rowChairs5, information, bottomSheet) = createRefs()

        Box(modifier = Modifier.constrainAs(xIcon) {
            start.linkTo(parent.start, 16.dp)
            top.linkTo(parent.top, 16.dp)
        }) { ExitIcon(onClickExit) }

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .cinemaStyle(clipRatio = 0.58f)
                .constrainAs(image) {
                    top.linkTo(xIcon.bottom, (-24).dp)
                },
        )

        val gap = 50
        /// region chairs
        RowOfPairOfChairs(
            pairList = listOf(
                Pair(ChairState.Available, ChairState.Available),
                Pair(ChairState.Available, ChairState.Available),
                Pair(ChairState.Taken, ChairState.Available),
            ),
            modifier = Modifier.constrainAs(rowChairs1) {
                top.linkTo(image.bottom, (-24).dp)
            }
        )

        RowOfPairOfChairs(
            pairList = listOf(
                Pair(ChairState.Available, ChairState.Available),
                Pair(ChairState.Selected, ChairState.Selected),
                Pair(ChairState.Available, ChairState.Available),
            ),
            modifier = Modifier.constrainAs(rowChairs2) {
                top.linkTo(rowChairs1.bottom, (-gap).dp)
            }
        )

        RowOfPairOfChairs(
            pairList = listOf(
                Pair(ChairState.Taken, ChairState.Available),
                Pair(ChairState.Selected, ChairState.Selected),
                Pair(ChairState.Taken, ChairState.Taken),
            ),
            modifier = Modifier.constrainAs(rowChairs3) {
                top.linkTo(rowChairs2.bottom, (-gap).dp)
            }
        )

        RowOfPairOfChairs(
            pairList = listOf(
                Pair(ChairState.Available, ChairState.Available),
                Pair(ChairState.Taken, ChairState.Taken),
                Pair(ChairState.Available, ChairState.Available),
            ),
            modifier = Modifier.constrainAs(rowChairs4) {
                top.linkTo(rowChairs3.bottom, (-gap).dp)
            }
        )
        RowOfPairOfChairs(
            pairList = listOf(
                Pair(ChairState.Taken, ChairState.Taken),
                Pair(ChairState.Taken, ChairState.Taken),
                Pair(ChairState.Available, ChairState.Available),
            ),
            modifier = Modifier.constrainAs(rowChairs5) {
                top.linkTo(rowChairs4.bottom, (-gap).dp)
            }
        )
        /// endregion

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(information) {
                    top.linkTo(rowChairs5.bottom)
                    bottom.linkTo(bottomSheet.top)
                },
            horizontalArrangement = Arrangement.SpaceAround,
        ) { Information() }

        BottomSheet(
            modifier = Modifier.constrainAs(bottomSheet) {
                bottom.linkTo(parent.bottom)
                top.linkTo(information.bottom)
            },
            onClickBuy = onClickBuy,
            buyTicketsUiState = state,
            doWhenSelectHour = doWhenSelectHour,
            doWhenSelectDay = doWhenSelectDay
        )
    }
}

@Composable
private fun Information() {
    SelectedRadioItem(chairState = ChairState.Available)
    SelectedRadioItem(chairState = ChairState.Taken)
    SelectedRadioItem(chairState = ChairState.Selected)
}

@Composable
private fun ExitIcon(
    onClickExit: () -> Unit
) {
    BlurredCard(
        onClick = { onClickExit() },
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
}

@Composable
private fun BottomSheet(
    onClickBuy: () -> Unit,
    doWhenSelectDay: (Day) -> Unit,
    doWhenSelectHour: (String) -> Unit,
    buyTicketsUiState: BuyTicketsUiState,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(buyTicketsUiState.days) {
                    DateChip(
                        it,
                        isSelected = it == buyTicketsUiState.selectedDay,
                        doWhenSelect = doWhenSelectDay
                    )
                }
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(buyTicketsUiState.timeList) {
                    HourChip(
                        it,
                        isSelected = it == buyTicketsUiState.selectedTime,
                        doWhenSelectHour = doWhenSelectHour,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "$${buyTicketsUiState.price}",
                        fontSize = 24.sp,
                        fontFamily = Sans,
                        fontWeight = FontWeight.Bold,
                        color = Black87,
                    )
                    Text(
                        text = "${buyTicketsUiState.ticketsCount} tickets",
                        fontSize = 11.sp,
                        fontFamily = Sans,
                        fontWeight = FontWeight.Normal,
                        color = Black60
                    )

                }
                PrimaryButton(
                    painter = painterResource(id = R.drawable.card),
                    onClick = onClickBuy,
                    text = "Buy Tickets",
                    modifier = Modifier.height(56.dp),
                )
            }
        }
    }
}


@Composable
fun SelectedRadioItem(
    chairState: ChairState
) {
    val name = when (chairState) {
        ChairState.Available -> "Available"
        ChairState.Taken -> "Taken"
        ChairState.Selected -> "Selected"
    }
    val color = when (chairState) {
        ChairState.Available -> White87
        ChairState.Taken -> DarkGray
        ChairState.Selected -> PrimaryLight
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Space(space = 8.dp)
        Text(
            text = name,
            fontFamily = Sans,
            fontWeight = FontWeight.Normal,
            color = White60,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_3a_xl")
@Composable
fun BuyTicketsScreenPrev() {
    BuyTicketsScreen(rememberNavController())
}