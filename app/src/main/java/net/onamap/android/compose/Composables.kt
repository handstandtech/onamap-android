package net.onamap.android.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Border
import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import net.onamap.android.dao.Photo
import net.onamap.android.model.StateData

class Composables {
    @Composable
    fun UsState(stateData: StateData, statePhotos: List<Photo>) {
        // A surface container using the 'background' color from the theme
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(text = stateData.fullName)
                },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
            ScrollableColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalGravity = Alignment.CenterHorizontally
            ) {
                CardView {
                    Row(
                        horizontalArrangement = Arrangement.Center
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
                                    start.linkTo(parent.start, margin = 16.dp)
                                    end.linkTo(text.start, margin = 16.dp)
                                    top.linkTo(parent.top, margin = 0.dp)
                                    bottom.linkTo(parent.bottom, margin = 0.dp)
                                }

                                constrain(text) {
                                    start.linkTo(stateImage.end, margin = 16.dp)
                                    end.linkTo(parent.end, margin = 16.dp)
                                    top.linkTo(parent.top, margin = 0.dp)
                                    bottom.linkTo(parent.bottom, margin = 0.dp)
                                }
                            }
                        ) {
                            StateImage(
                                icon = stateData.icon,
                                modifier = Modifier
                                    .layoutId(stateImageConstraint)
                                    .wrapContentHeight()
                            )
                            Column(
                                horizontalGravity = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .layoutId(countTextConstraint)
                            ) {
                                Box(
                                    padding = 16.dp,
                                    gravity = Alignment.Center
                                ) {
                                    Text(
                                        text = "${statePhotos.size}",
                                        style = appTypography.h1,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Photos",
                                        style = appTypography.h5,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }

                        }
                    }
                }
                for (photo in statePhotos) {
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
}

@Composable
fun StateImage(@DrawableRes icon: Int, modifier: Modifier) {
    Box(
        backgroundColor = Color.White,
        modifier = modifier.wrapContentSize(),
        padding = 4.dp,
        border = Border(1.dp, Color.Red)
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
//                        .size(150.dp)
                        .wrapContentHeight()
                )
            }
        }
    }
}

@Composable
fun CardView(content: @Composable () -> Unit) {
    Box(
        padding = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        border = Border(1.dp, Color.Blue)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            border = Border(
                size = 1.dp,
                color = Color.DarkGray
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                border = Border(
                    size = 1.dp,
                    color = Color.Green
                )
            ) {
                Box(
                    padding = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    gravity = Alignment.CenterStart,
                    border = Border(
                        size = 1.dp,
                        color = Color.Yellow
                    )
                ) {
                    content()
                }
            }
        }
    }
}