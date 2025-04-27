package net.onamap.android.compose.usstate

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
        CoilImageWithCrossfade(
            data = photo.url_m!!,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
    }
}