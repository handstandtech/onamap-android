package net.onamap.android.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun StateImage(@DrawableRes icon: Int, modifier: Modifier = Modifier, sizeDp: Dp) {
    val shape = RoundedCornerShape(4.dp)
    val stateSurroundingColor = MaterialTheme.colorScheme.background
    Box(
        modifier = modifier
            .padding(4.dp)
            .background(stateSurroundingColor)
            .wrapContentSize(),
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .background(stateBackgroundColor)
                .wrapContentSize()
        ) {
            Box(
                modifier = Modifier.wrapContentSize()
            ) {
                Text("Icon then Image goes here")
//                Icon(
//                    Resources.getSystem().getDrawable(icon)), null, modifier = Modifier
//                        .size(sizeDp)
//                )
//                Image(
//                    asset = imageResource(id = icon),
//                    colorFilter = ColorFilter.tint(stateSurroundingColor),
//                    modifier = Modifier
//                        .size(sizeDp)
//                )
            }
        }
    }
}