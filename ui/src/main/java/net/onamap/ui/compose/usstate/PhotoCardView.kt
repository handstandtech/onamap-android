package net.onamap.android.compose.usstate

import androidx.compose.runtime.Composable
import net.onamap.ui.compose.CardView
import net.onamap.models.models.Photo
import androidx.compose.material3.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PhotoCardView(photo: Photo) {
    CardView {
        AsyncImage(
            model = photo.url_m,
            contentDescription = "Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
    }
}