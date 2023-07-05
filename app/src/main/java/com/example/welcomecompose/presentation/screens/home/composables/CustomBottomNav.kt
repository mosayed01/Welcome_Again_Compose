package com.example.welcomecompose.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.screens.util.Space
import com.example.welcomecompose.presentation.ui.theme.Black87
import com.example.welcomecompose.presentation.ui.theme.OnPrimaryLight
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight
import com.example.welcomecompose.presentation.ui.theme.Typography

@Composable
fun CustomBottomNav(
    onNavigateToBuyTicketsScreen: () -> Unit
) {
    BottomNavigation(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        NavigationItem(painter = painterResource(id = R.drawable.movie), isSelected = true)
        NavigationItem(painter = painterResource(id = R.drawable.ic_search))
        NavigationItem(
            painter = painterResource(id = R.drawable.ticket),
            onClick = onNavigateToBuyTicketsScreen,
        ) { Badge(count = 5) }
        NavigationItem(painter = painterResource(id = R.drawable.user))
    }
}

@Composable
private fun RowScope.NavigationItem(
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
                    Space(space = 4.dp)
                    it()
                }
            }

        },
    )
}

@Composable
private fun Badge(
    count: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = count.toString(),
        textAlign = TextAlign.Center,
        color = OnPrimaryLight,
        style = Typography.bodySmall,
        modifier = modifier
            .size(18.dp)
            .clip(CircleShape)
            .background(PrimaryLight),
    )
}