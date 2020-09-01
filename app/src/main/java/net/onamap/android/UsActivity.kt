package net.onamap.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import net.onamap.android.compose.CardView
import net.onamap.android.compose.MyApplicationTheme
import net.onamap.android.compose.StateImage
import net.onamap.android.compose.appTypography
import net.onamap.android.model.StateData
import net.onamap.android.model.States

class UsActivity : AppCompatActivity() {

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }
    }

    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Providers(
                    AmbientBackPressHandler provides backPressHandler
                ) {
                    // Your root composable goes here
                    Root.Content(Root.Routing.UsMap)
                }
            }
        }
    }
}


@Composable
fun StatesList(
    onStateClicked: (StateData) -> Unit = {},
    states: List<StateData>
) {
    Scaffold(
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
fun StatesListPreview() {
    StatesList(
        states = States.states.subList(0, 10)
    )
}