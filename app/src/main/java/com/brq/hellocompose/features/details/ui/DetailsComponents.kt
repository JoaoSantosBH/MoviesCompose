package com.brq.hellocompose.features.details.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brq.hellocompose.R
import com.brq.hellocompose.ui.theme.DropDownSurfaceColor
import com.brq.hellocompose.ui.theme.OrangeBrqColor


@Composable
fun CardStatisticLayout(
    contentText: String,
    icon: Int,
    title: Int
) {
    val cardTitleColor = OrangeBrqColor
    val surfaceColor = DropDownSurfaceColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(16.dp),
        border = BorderStroke(1.dp, Color.Gray),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = surfaceColor)
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {


                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = cardTitleColor
                    )
                    Text(
                        text = stringResource(id = title),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.labelMedium.copy(color = cardTitleColor)
                    )
                }
                Text(
                    contentText,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(start= 4.dp, top = 8.dp),
                    style = MaterialTheme.typography.labelLarge.copy(color = Color.White)
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewCard() {
    CardStatisticLayout("CURTIDAS", R.drawable.icon_return, R.string.first_card_title)
}