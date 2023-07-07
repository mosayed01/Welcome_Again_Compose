package com.example.welcomecompose.presentation.screens.buy_tickets.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RowOfPairOfChairs(
    pairList: List<Pair<ChairState, ChairState>>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pairList.size) {
            PairChairs(
                pair = pairList[it],
                size = 35,
                modifier = Modifier.graphicsLayer {
                    val rotateDegree = if (it == 0) 10f else if (it == 1) 0f else -10f
                    val translatedY = if (it == 1) 30f else 0f
                    rotationZ = rotateDegree
                    translationY = translatedY
                },
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF000000)
@Composable
fun RowOfPairOfChairsPreview() {
    RowOfPairOfChairs(
        pairList = listOf(
            Pair(ChairState.Taken, ChairState.Available),
            Pair(ChairState.Selected, ChairState.Selected),
            Pair(ChairState.Taken, ChairState.Taken),
        )
    )
}