package com.example.welcomecompose.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.ui_models.ChairState
import com.example.welcomecompose.presentation.ui.theme.DarkGray

@Composable
fun PairChairs(
    pair: Pair<ChairState, ChairState>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.chair_container),
            contentDescription = "",
            modifier = Modifier.size((75 * 2 + 38).dp),
            tint = DarkGray
        )
        Row(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            ChairItem(initialChairState = pair.first, modifier = Modifier.size(75.dp))
            ChairItem(initialChairState = pair.second, modifier = Modifier.size(75.dp))
        }
    }
}


@Preview
@Composable
fun PairChairsPreview() {
    PairChairs(Pair(ChairState.Selected, ChairState.Available))
}