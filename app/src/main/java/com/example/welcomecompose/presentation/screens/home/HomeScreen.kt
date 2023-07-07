package com.example.welcomecompose.presentation.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.Chip
import com.example.welcomecompose.presentation.screens.Screens
import com.example.welcomecompose.presentation.screens.home.composables.Carousel
import com.example.welcomecompose.presentation.screens.home.composables.GradientOverlay
import com.example.welcomecompose.presentation.ui.theme.Black38
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(initialPage = 1)
    var homeUiState by remember { mutableStateOf(HomeUiState()) }
    HomeScreenContent(
        modifier = modifier,
        pagerState = pagerState,
        state = homeUiState,
        listener = object : HomeScreenInteractionsListener { // cause I don't need a view model
            override fun onClickImage() {
                navController.navigate(Screens.Booking.route)
            }

            override fun onClickNowShowing() {
                homeUiState =
                    homeUiState.copy(
                        nowShowingChip = homeUiState.nowShowingChip.copy(isSelected = true),
                        comingSoonChip = homeUiState.comingSoonChip.copy(isSelected = false)
                    )
            }

            override fun onClickComingSoon() {
                homeUiState =
                    homeUiState.copy(
                        nowShowingChip = homeUiState.nowShowingChip.copy(isSelected = false),
                        comingSoonChip = homeUiState.comingSoonChip.copy(isSelected = true)
                    )
            }

            override fun onNavigateToBuyTicketsScreen() {
                navController.navigate(Screens.BuyTickets.route)
            }
        },
    )
}


@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class,
)
@Composable
fun HomeScreenContent(
    pagerState: PagerState,
    state: HomeUiState,
    listener: HomeScreenInteractionsListener,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Crossfade(
            targetState = state.images[pagerState.currentPage],
            animationSpec = tween(800, easing = FastOutSlowInEasing),
            label = "Cross Fade Animation For Background"
        ) { selectedImageRes ->
            GradientOverlay {
                Image(
                    painter = painterResource(id = selectedImageRes),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .graphicsLayer {
                            renderEffect = BlurEffect(
                                100f,
                                100f,
                            )
                            clip = false
                        }
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            ) {
                Chip(
                    text = state.nowShowingChip.title,
                    isSelected = state.nowShowingChip.isSelected,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    doWhenClick = listener::onClickNowShowing
                )
                Chip(
                    text = state.comingSoonChip.title,
                    isSelected = state.comingSoonChip.isSelected,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    doWhenClick = listener::onClickComingSoon
                )
            }

            Carousel(
                onClickItem = listener::onClickImage,
                pagerState = pagerState,
                items = state.images,
                modifier = Modifier.padding(top = 24.dp)
            ) { modifier, item ->
                Image(
                    painter = painterResource(id = item),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                )
            }

            Row(
                modifier = Modifier.padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.time),
                    contentDescription = "time",
                    tint = Black38,
                    modifier = Modifier.size(16.dp),
                )
                Text(
                    text = state.time,
                    style = Typography.bodySmall,
                    color = Black87,
                )
            }

            Text(
                text = state.title,
                style = Typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp)
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top = 16.dp),
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
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun HomeScreenPrev() {
    HomeScreenContent(
        pagerState = PagerState(initialPage = 1),
        state = HomeUiState(),
        listener = object : HomeScreenInteractionsListener {
            override fun onClickImage() {
                TODO("Not yet implemented")
            }

            override fun onClickNowShowing() {
                TODO("Not yet implemented")
            }

            override fun onClickComingSoon() {
                TODO("Not yet implemented")
            }

            override fun onNavigateToBuyTicketsScreen() {
                TODO("Not yet implemented")
            }

        }
    )
}