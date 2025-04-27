package net.onamap.android.compose.usstate

import androidx.compose.runtime.Composable
import net.onamap.ui.compose.CardView
import net.onamap.models.models.Photo

import androidx.compose.material3.*

@Composable
fun PhotoCardView(photo: Photo) {
    CardView {
        Text("Image goes here ${photo.url_m}")
//        CoilImageWithCrossfade(
//            data = photo.url_m!!,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(240.dp)
//        )
    }
}