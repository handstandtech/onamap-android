package net.onamap.android.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import net.onamap.android.R
import net.onamap.android.dao.Photo
import net.onamap.android.model.StateData

@Composable
fun UsState(
    onUpClicked: () -> Unit = {},
    state: StateData,
    photos: List<Photo>
) {
    // A surface container using the 'background' color from the theme
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(text = state.fullName)
            },
            backgroundColor = Color.White,
            navigationIcon = {
                IconButton(onClick = {
                    onUpClicked()
                }) {
                    Icon(Icons.Filled.ArrowBack)
                }
            }
        )
        ScrollableColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalGravity = Alignment.CenterHorizontally
        ) {
            StateHeaderCard(
                icon = state.icon,
                photoCount = photos.size
            )
            for (photo in photos) {
                CardView {
                    val coilImageId = "coilImage"
                    ConstraintLayout(
                        modifier = Modifier.fillMaxWidth()
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
        }
    }
}

@Composable
fun StateHeaderCard(@DrawableRes icon: Int, photoCount: Int) {
    CardView {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
        ) {
            val stateImageConstraint = "stateImageId"
            val countTextConstraint = "textId"
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight(),
                constraintSet = ConstraintSet {
                    val stateImage = createRefFor(stateImageConstraint)
                    val text = createRefFor(countTextConstraint)

                    constrain(stateImage) {
                        start.linkTo(parent.start)
                        end.linkTo(text.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }

                    constrain(text) {
                        start.linkTo(stateImage.end)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                }
            ) {
                StateImage(
                    icon = icon,
                    modifier = Modifier
                        .layoutId(stateImageConstraint),
                    sizeDp = 100.dp
                )
                Column(
                    horizontalGravity = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .wrapContentSize()
                        .layoutId(countTextConstraint)
                        .padding(
                            bottom = 8.dp
                        )
                ) {
                    Text(
                        text = "$photoCount",
                        style = appTypography.h2,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = if (photoCount == 1) "Photo" else "Photos",
                        style = appTypography.h5,
                        textAlign = TextAlign.Center,
                    )
                }

            }
        }
    }
}

@Composable
fun StateImage(@DrawableRes icon: Int, modifier: Modifier = Modifier, sizeDp: Dp) {
    Box(
        backgroundColor = Color.White,
        modifier = modifier.wrapContentSize(),
        padding = 4.dp
    ) {
        Box(
            backgroundColor = stateBackground,
            modifier = Modifier.wrapContentSize()
        ) {
            Box(
                modifier = Modifier.wrapContentSize()
            ) {
                Image(
                    asset = imageResource(id = icon),
                    modifier = Modifier
                        .size(sizeDp)
                )
            }
        }
    }
}

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