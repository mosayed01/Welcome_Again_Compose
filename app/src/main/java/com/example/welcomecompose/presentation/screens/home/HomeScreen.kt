package com.example.welcomecompose.presentation.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.PrimaryChip
import com.example.welcomecompose.presentation.screens.Screens
import com.example.welcomecompose.presentation.screens.util.Space
import com.example.welcomecompose.presentation.ui.theme.Black38
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val pagerState = rememberPagerState(initialPage = 1)
    var homeUiState by remember { mutableStateOf(HomeUiState()) }
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                NavigationItem(painter = painterResource(id = R.drawable.movie), isSelected = true)
                NavigationItem(painter = painterResource(id = R.drawable.ic_search))
                NavigationItem(
                    painter = painterResource(id = R.drawable.ticket),
                    onClick = {
                        navController.navigate(Screens.BuyTickets.route)
                    }
                ) { Badge(count = 5) }
                NavigationItem(painter = painterResource(id = R.drawable.user))
            }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Crossfade(
                targetState = homeUiState.images[pagerState.currentPage],
                animationSpec = tween(200, easing = FastOutSlowInEasing),
            ) { BackgroundWithBlurredImage(painter = painterResource(id = it)) }
            HomeScreenContent(
                paddingValues = it,
                pagerState = pagerState,
                homeUiState = homeUiState,
                onClickImage = {
                    navController.navigate(Screens.Booking.route)
                },
                onClickComingSoon = {
                    homeUiState =
                        homeUiState.copy(
                            nowShowingChip = homeUiState.nowShowingChip.copy(isSelected = false),
                            comingSoonChip = homeUiState.comingSoonChip.copy(isSelected = true)
                        )
                },
                onClickNowShowing = {
                    homeUiState =
                        homeUiState.copy(
                            nowShowingChip = homeUiState.nowShowingChip.copy(isSelected = true),
                            comingSoonChip = homeUiState.comingSoonChip.copy(isSelected = false)
                        )
                }
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    pagerState: PagerState,
    homeUiState: HomeUiState,
    onClickImage: () -> Unit,
    onClickNowShowing: () -> Unit,
    onClickComingSoon: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues), // ! I couldn't use paddingValues for Scaffold
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Space(space = 24)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            PrimaryChip(
                text = homeUiState.nowShowingChip.title,
                isSelected = homeUiState.nowShowingChip.isSelected,
                modifier = Modifier.padding(horizontal = 8.dp),
                doWhenClick = onClickNowShowing
            )
            Space(space = 8)
            PrimaryChip(
                text = homeUiState.comingSoonChip.title,
                isSelected = homeUiState.comingSoonChip.isSelected,
                modifier = Modifier.padding(horizontal = 8.dp),
                doWhenClick = onClickComingSoon
            )
        }
        Space(space = 24)

        HorizontalImages(
            onClickImage = onClickImage,
            pagerState = pagerState,
            images = homeUiState.images
        )

        Space(space = 24)

        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.time),
                contentDescription = "time",
                tint = Black38,
                modifier = Modifier.size(16.dp),
            )
            Space(space = 4)
            Text(
                text = homeUiState.time,
                fontFamily = Sans,
                fontWeight = FontWeight.Normal,
                color = Black87,
                fontSize = 12.sp
            )
        }
        Space(space = 16)

        Text(
            text = homeUiState.title,
            fontFamily = Sans,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
        )

        Space(space = 16)

        FlowRow(
            horizontalArrangement = Arrangement.Center,
        ) {
            homeUiState.genres.forEach {
                PrimaryChip(
                    text = it,
                    isEnabled = false,
                    unSelectedTextColor = Black87,
                    borderColor = Black8,
                    fontSize = 12.sp
                )
                Space(space = 4)
            }
        }
    }
}


@Composable
fun Badge(
    count: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = count.toString(),
        fontFamily = Sans,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        color = OnPrimaryLight,
        modifier = modifier
            .size(18.dp)
            .clip(CircleShape)
            .background(PrimaryLight),
    )
}

@Composable
fun RowScope.NavigationItem(
    painter: Painter,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    moreContent: (@Composable () -> Unit)? = null,
) {
    BottomNavigationItem(
        selected = isSelected,
        onClick = { onClick() },
        icon = {
            Row(
                modifier = modifier
                    .size(52.dp)
                    .clip(if (isSelected) CircleShape else RoundedCornerShape(0.dp))
                    .background(if (isSelected) PrimaryLight else Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painter,
                    contentDescription = "",
                    tint = if (isSelected) OnPrimaryLight else Black87
                )
                moreContent?.let {
                    Space(space = 4)
                    it()
                }
            }

        },
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalImages(
    onClickImage: () -> Unit,
    pagerState: PagerState,
    images: List<Int>,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = pagerState,
        pageCount = images.size,
        contentPadding = PaddingValues(horizontal = 32.dp),
        modifier = modifier
    ) {
        val animatedScale by animateFloatAsState(
            targetValue = if (it == pagerState.currentPage) 1f else 0.9f,
            animationSpec = tween(durationMillis = 200)
        )

        Image(
            painter = painterResource(id = images[it % images.size]),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(3 / 4f)
                .scale(animatedScale)
                .clip(MaterialTheme.shapes.extraLarge)
                .clickable { onClickImage() }
        )
    }
}


@Composable
fun BackgroundWithBlurredImage(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxSize()
        .drawWithContent {
            drawContent()
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.White.copy(0.1f),
                        Color.White.copy(0.2f),
                        Color.White.copy(0.3f),
                        Color.White.copy(0.4f),
                        Color.White.copy(0.5f),
                        Color.White.copy(0.7f),
                        Color.White.copy(0.8f),
                        Color.White.copy(0.9f),
                        Color.White,
                    ),
                ),
                topLeft = Offset(0f, size.height / 5),
            )

        }) {
        Image(
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .blur(radius = 50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        )
    }
}


@Preview
@Composable
fun HomeScreenPrev() {
    HomeScreen(rememberNavController())
}