package net.onamap.android.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun StateImage(@DrawableRes icon: Int, modifier: Modifier = Modifier, sizeDp: Dp) {
    val shape = RoundedCornerShape(4.dp)
    val stateSurroundingColor = MaterialTheme.colors. background
    Box(
        backgroundColor = stateSurroundingColor,
        modifier = modifier
            .padding(4.dp)
            .wrapContentSize(),
        shape = shape
    ) {
        Box(
            shape = shape,
            backgroundColor = stateBackgroundColor,
            modifier = Modifier
                .padding(4.dp)
                .wrapContentSize()
        ) {
            Box(
                modifier = Modifier.wrapContentSize()
            ) {
                Image(
                    asset = imageResource(id = icon),
                    colorFilter = ColorFilter.tint(stateSurroundingColor),
                    modifier = Modifier
                        .size(sizeDp)
                )
            }
        }
    }
}