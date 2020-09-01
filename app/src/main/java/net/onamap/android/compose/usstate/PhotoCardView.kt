package net.onamap.android.compose.usstate

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import net.onamap.android.compose.CardView
import net.onamap.android.dao.Photo

@Composable
fun PhotoCardView(photo: Photo) {
    CardView {
        val coilImageId = "coilImage"
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            constraintSet = ConstraintSet {
                val coilImage = createRefFor(coilImageId)
                constrain(coilImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            }
        ) {
            CoilImageWithCrossfade(
                data = photo.url_m!!,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(240.dp)
                    .layoutId(coilImageId)
            )
        }
    }
}