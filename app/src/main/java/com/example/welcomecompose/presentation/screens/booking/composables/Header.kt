package com.example.welcomecompose.presentation.screens.booking.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.welcomecompose.R
import com.example.welcomecompose.presentation.composables.BlurredCard
import com.example.welcomecompose.presentation.composables.ExitIcon
import com.example.welcomecompose.presentation.screens.util.Space
import com.example.welcomecompose.presentation.ui.theme.Sans
import com.example.welcomecompose.presentation.ui.theme.White38
import com.example.welcomecompose.presentation.ui.theme.White60

@Composable
fun Header(
    time: String,
    onClickExit: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ExitIcon(onClickExit)

        BlurredCard(onClick = { /*TODO*/ }, modifier = Modifier) {
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.time),
                    contentDescription = "date",
                    tint = White38,
                    modifier = Modifier.size(16.dp),
                )
                Space(space = 4.dp)
                Text(
                    text = time,
                    fontSize = 12.sp,
                    fontFamily = Sans,
                    fontWeight = FontWeight.Normal,
                    color = White60
                )
            }
        }
    }
}