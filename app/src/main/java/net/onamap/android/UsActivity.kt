package net.onamap.android

import android.os.Bundle
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

class UsActivity : BaseActivity() {

//    private var recyclerViewAdapter: StatesRVAdapter? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_usmap)
//        val recyclerView = findViewById<View>(R.id.states_recyclerview) as RecyclerView
//        recyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerViewAdapter = StatesRVAdapter()
//        recyclerView.adapter = recyclerViewAdapter
//    }


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
//                StatesList(
//                    activity = this,
//                    states = States.states
//                )

            }
        }


    }
}


@Composable
fun StatesList(
    onStateClicked: (StateData) -> Unit = {},
    states: List<StateData>
) {
    ScrollableColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        for (state in states) {
            CardView(modifier = Modifier.clickable(onClick = {
//                val intent = Intent(activity!!, ComposeStateActivity::class.java)
//                intent.putExtra("state", state)
//                activity.startActivity(intent)
                onStateClicked(state)
            })) {
//                ClickableText(text = , onClick = )

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
}

@Preview
@Composable
fun StatesListPreview() {
    StatesList(
        states = States.states.subList(0, 10)
    )
}