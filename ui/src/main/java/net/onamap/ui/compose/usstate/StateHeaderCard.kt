package net.onamap.android.compose.usstate

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.onamap.ui.compose.CardView
import net.onamap.ui.compose.StateImage


object StateHeaderCard {
    @Composable
    fun StateHeaderCard(@DrawableRes icon: Int, photoCount: Int) {
        CardView {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                val stateImageConstraint = "stateImageId"
                val countTextConstraint = "textId"
                StateImage(
                    icon = icon,
                    modifier = Modifier
                        .layoutId(stateImageConstraint),
                    sizeDp = 100.dp
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .wrapContentSize()
                        .layoutId(countTextConstraint)
                        .padding(
                            bottom = 8.dp
                        )
                ) {
                    Text(
                        text = "$photoCount",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = if (photoCount == 1) "Photo" else "Photos",
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                    )
                }


            }
        }
    }
}