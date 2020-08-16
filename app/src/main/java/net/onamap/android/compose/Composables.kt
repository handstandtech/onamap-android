package net.onamap.android.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Border
import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import net.onamap.android.dao.Photo
import net.onamap.android.model.StateData

class Composables {

    @Preview(showBackground = true)
    @Composable
    fun MyScaffold() {
        val categories = listOf("hi", "bye")
        var currentCategory = categories[0]
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = "M",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }, elevation = 0.dp)
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {}) {
                    Icon(asset = Icons.Default.Add)
                }
            },
            bodyContent = {
                Column {
                    TabRow(
                        items = categories,
                        selectedIndex = 0
                    ) { index, currentTabValue ->
                        Tab(
                            selected = categories.indexOf(currentCategory) == index,
                            onSelected = { currentCategory = (categories[index]) }
                        )
                        {
                            Text(
                                text = currentTabValue,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        )
    }


    @Composable
    fun UsState(stateData: StateData, statePhotos: List<Photo>) {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalGravity = Alignment.CenterHorizontally
            ) {
                TopAppBar(
                    title = {
                        Text(text = stateData.fullName)
                    },
                    contentColor = Color.White
                )
                CardView {
                    StateImage(stateData.icon)
                }
                for (photo in statePhotos) {
                    CardView {
                        CoilImageWithCrossfade(
                            data = photo.url_m!!,
                            modifier = Modifier.size(200.dp, 200.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun StateImage(@DrawableRes icon: Int) {
        Box(
            backgroundColor = Color.White,
            modifier = Modifier.wrapContentSize(),
            padding = 4.dp,
            shape = RoundedCornerShape(8.dp),
        ) {
            Box(
                backgroundColor = Color(android.graphics.Color.parseColor("#3EDC84")),
                modifier = Modifier.wrapContentSize()
            ) {
                Image(
                    asset = imageResource(id = icon),
                    modifier = Modifier.size(200.dp, 200.dp)
                )
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
            gravity = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                border = Border(
                    size = 1.dp,
                    color = Color.DarkGray
                )
            ) {
                Box(
                    padding = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    gravity = Alignment.Center
                ) {
                    Box(
                        padding = 8.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        gravity = Alignment.Center
                    ) {
                        content()
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(
            text = "Hello $name!",
            style = TextStyle(fontSize = 20.sp)
        )
    }

    @Composable
    fun MyButton() {
        Button(onClick = {}) {
            Text(
                text = "Jetpack Compose",
                modifier = Modifier.padding(4.dp)
            )
        }
    }

    //@Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
            Greeting("Android")
        }
    }

}