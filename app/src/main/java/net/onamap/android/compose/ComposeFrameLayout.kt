package net.onamap.android.compose

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.foundation.Border
import androidx.compose.foundation.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.VerticalScroller
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import net.onamap.android.R

class ComposeFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        setContent(recomposer = Recomposer.current()) {
            CoilImage(
                data = R.drawable.ak
            )
//            MyApplicationTheme {
//                UsState()
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsState() {
    // A surface container using the 'background' color from the theme
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        ScrollableColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalGravity = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Alaska",
                style = TextStyle(fontSize = 40.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                backgroundColor = Color.Green,
                gravity = Alignment.Center,
                modifier = Modifier.wrapContentWidth(),
                shape = RoundedCornerShape(10),
                border = Border(
                    size = 1.dp,
                    color = Color.DarkGray
                )
            ) {
                Image(
                    asset = imageResource(id = R.drawable.ak),
                    modifier = Modifier.size(200.dp, 200.dp)
                )
            }
            CoilImage(
                data = "https://live.staticflickr.com/3826/11697289533_cfd6282b37.jpg",
                modifier = Modifier.size(500.dp, 300.dp)
            )
        }
    }
}

private val TAG = "TAG"

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