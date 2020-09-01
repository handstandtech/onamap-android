package net.onamap.android.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import net.onamap.android.R
import net.onamap.android.compose.usstate.StateHeaderCard.StateHeaderCard
import net.onamap.android.dao.Photo


@Composable
fun CardView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(4.dp)
    Card(
        shape = shape,
        modifier = modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .drawShadow(elevation = 2.dp, shape = shape, clip = true)
            .clickable(onClick = onClick)
    ) {
        content()
    }
}


// Previews

private val mockPhotos = listOf(
    Photo(
        id = "6783548026",
        url_sq = "https://live.staticflickr.com/7058/6783548026_c482f9a8e4_s.jpg",
        url_s = "https://live.staticflickr.com/7058/6783548026_c482f9a8e4_m.jpg",
        url_m = "https://live.staticflickr.com/7058/6783548026_c482f9a8e4.jpg",
        title = "Jasper, Alabama",
        lat = 33.831268,
        lng = -87.281372,
        link = "https://www.flickr.com/photos/84057763@N00/6783548026",
        datetaken = 1325681434000,
        placeId = 6317208087035904,
        country = "United States",
        region = "Alabama",
        city = "Jasper"
    )
)

@Preview
@Composable
private fun AlaskaStateHeaderCardPreview() {
    StateHeaderCard(icon = R.drawable.ak, photoCount = 1)
}

@Preview
@Composable
private fun CaliforniaStateHeaderCardPreview() {
    StateHeaderCard(icon = R.drawable.ca, photoCount = 90)
}