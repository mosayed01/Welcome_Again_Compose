package com.example.welcomecompose.presentation.screens.home.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> Carousel(
    onClickItem: () -> Unit,
    pagerState: PagerState,
    items: List<T>,
    modifier: Modifier = Modifier,
    content: @Composable (modifier: Modifier, item: T) -> Unit
) {
    HorizontalPager(
        state = pagerState,
        pageCount = items.size,
        contentPadding = PaddingValues(horizontal = 32.dp),
        modifier = modifier
    ) {
        val animatedScale by animateFloatAsState(
            targetValue = if (it == pagerState.currentPage) 1f else 0.9f,
            animationSpec = tween(durationMillis = 200)
        )
        content(
            Modifier
                .aspectRatio(3 / 4f)
                .scale(animatedScale)
                .clip(MaterialTheme.shapes.extraLarge)
                .clickable { onClickItem() },
            items[it % items.size]
        )
    }
}