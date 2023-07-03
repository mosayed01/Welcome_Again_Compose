package com.example.welcomecompose.presentation.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.PrimaryChip
import com.example.welcomecompose.presentation.screens.util.Space
import com.example.welcomecompose.presentation.ui.theme.Black38
import com.example.welcomecompose.presentation.ui.theme.Black8
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Sans

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
)
@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                NavigationItem(painter = painterResource(id = R.drawable.movie), isSelected = true)
                NavigationItem(painter = painterResource(id = R.drawable.ic_search))
                NavigationItem(painter = painterResource(id = R.drawable.ticket)) { Badge(count = 5) }
                NavigationItem(painter = painterResource(id = R.drawable.user))
            }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val pagerState = rememberPagerState(initialPage = 1)
            val images = listOf(
                R.drawable.img_1,
                R.drawable.img_3,
                R.drawable.img_2,
            )
            Crossfade(
                targetState = images[pagerState.currentPage],
                animationSpec = tween(200, easing = FastOutSlowInEasing),
            ) { BackgroundWithBlurredImage(painter = painterResource(id = it)) }
            HomeScreenContent(paddingValues = it, pagerState = pagerState, images = images)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    pagerState: PagerState,
    images: List<Int>

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
                text = "New Showing",
                isSelected = true,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            Space(space = 8)
            PrimaryChip(
                text = "Coming Soon",
                isSelected = false,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
        }
        Space(space = 24)

        HorizontalImages(pagerState = pagerState, images = images)

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
                text = "2h 23m",
                fontFamily = Sans,
                fontWeight = FontWeight.Normal,
                color = Black87,
                fontSize = 12.sp
            )
        }
        Space(space = 16)

        Text(
            text = "Fantastic Beasts: The \nSecrets of Dumbledore",
            fontFamily = Sans,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
        )

        Space(space = 16)

        Row {
            PrimaryChip(
                text = "Fantasy",
                isEnabled = false,
                unSelectedTextColor = Black87,
                borderColor = Black8,
                fontSize = 12.sp
            )
            Space(space = 4)
            PrimaryChip(
                text = "Adventure",
                isEnabled = false,
                unSelectedTextColor = Black87,
                borderColor = Black8,
                fontSize = 12.sp
            )
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
    moreContent: (@Composable () -> Unit)? = null,
) {
    BottomNavigationItem(
        selected = isSelected,
        onClick = { /*TODO*/ },
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
    pagerState: PagerState,
    images: List<Int>,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = pagerState,
        pageCount = images.size,
        contentPadding = PaddingValues(horizontal = 32.dp),
        pageSpacing = 8.dp,
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
    HomeScreen()
}