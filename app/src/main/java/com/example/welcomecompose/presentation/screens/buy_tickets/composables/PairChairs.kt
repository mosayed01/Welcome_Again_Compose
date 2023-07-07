package com.example.welcomecompose.presentation.screens.buy_tickets.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.ui.theme.DarkGray
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight

@Composable
fun PairChairs(
    pair: Pair<ChairState, ChairState>,
    modifier: Modifier = Modifier,
    size: Dp = 65.dp
) {
    var pairState by remember {
        mutableStateOf(pair)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.chair_container),
            contentDescription = "",
            modifier = Modifier
                .width(size * 2 + 38.dp)
                .height(size),
            tint = if ((pairState.first == pairState.second) && (pairState.second == ChairState.Selected)) {
                PrimaryLight.copy(alpha = 0.38f)
            } else if ((pairState.first == pairState.second) && (pairState.second == ChairState.Taken)) {
                DarkGray.copy(alpha = 0.2f)
            } else {
                DarkGray.copy(alpha = 0.6f)
            }
        )
        Row(
            modifier = Modifier
                .matchParentSize()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ChairItem(chairState = pairState.first, modifier = Modifier.size(size)) {
                pairState = pairState.copy(first = it.nextState())
            }
            ChairItem(chairState = pairState.second, modifier = Modifier.size(size)) {
                pairState = pairState.copy(second = it.nextState())
            }
        }
    }
}


@Preview
@Composable
fun PairChairsPreview() {
    PairChairs(Pair(ChairState.Taken, ChairState.Taken))
}