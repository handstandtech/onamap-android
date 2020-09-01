package net.onamap.android.compose.usstate

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.onamap.android.compose.CardView
import net.onamap.android.compose.StateImage
import net.onamap.android.compose.appTypography


object StateHeaderCard {
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
}