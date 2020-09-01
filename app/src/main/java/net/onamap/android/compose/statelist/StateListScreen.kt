package net.onamap.android.compose.statelist

import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import net.onamap.android.compose.CardView
import net.onamap.android.compose.StateImage
import net.onamap.android.compose.appTypography
import net.onamap.android.model.StateData
import net.onamap.android.model.States

@Composable
fun StateListScreen(
    onStateClicked: (StateData) -> Unit = {},
    states: List<StateData>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "handstandsam.onamap.net")
                }
            )
        },
        bodyContent = {
            LazyColumnFor(
                items = states,
                modifier = Modifier.fillMaxSize()
            ) { state ->
                CardView(onClick = {
                    onStateClicked(state)
                }) {
                    Box(
                        padding = 8.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        gravity = Alignment.CenterStart
                    ) {
                        Row(
                            verticalGravity = Alignment.CenterVertically,
                        ) {
                            StateImage(
                                icon = state.drawableResThumb,
                                sizeDp = 30.dp
                            )
                            Text(
                                text = state.fullName,
                                style = appTypography.subtitle1,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(
                                    start = 8.dp
                                )
                            )
                        }
                    }
                }
            }
        })
}

@Preview
@Composable
fun StateListScreenPreview() {
    StateListScreen(
        states = States.states.subList(0, 10)
    )
}