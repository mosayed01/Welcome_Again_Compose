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
import com.example.welcomecompose.presentation.ui.theme.PrimaryLight

@Composable
fun PairChairs(
    pair: Pair<ChairState, ChairState>,
    modifier: Modifier = Modifier,
    size: Int = 75
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.chair_container),
            contentDescription = "",
            modifier = Modifier.size((size * 2 + 38).dp),
            tint = if ((pair.first == pair.second) && (pair.second == ChairState.Selected)) {
                PrimaryLight.copy(alpha = 0.38f)
            } else if ((pair.first == pair.second) && (pair.second == ChairState.Taken)) {
                DarkGray.copy(alpha = 0.2f)
            } else {
                DarkGray.copy(alpha = 0.6f)
            }
        )
        Row(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            ChairItem(initialChairState = pair.first, modifier = Modifier.size(size.dp))
            ChairItem(initialChairState = pair.second, modifier = Modifier.size(size.dp))
        }
    }
}


@Preview
@Composable
fun PairChairsPreview() {
    PairChairs(Pair(ChairState.Taken, ChairState.Taken))
}