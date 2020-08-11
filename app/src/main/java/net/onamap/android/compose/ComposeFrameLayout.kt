package net.onamap.android.compose

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
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
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Row {
                Greeting("Row 1")
                MyButton()
            }
            Row {
                Greeting("Row 2")
                MyButton()
            }
            Row {

                Greeting("Image")
                CoilImage(
                    data = "https://compose.academy/doks-theme/assets/images/layout/logo.png"
                )
                CoilImageWithCrossfade(
                    data = "https://loremflickr.com/300/300",
                    onRequestCompleted = {
                        Log.d(TAG, "Request Completed")
                    }
                )
            }
        }

    }
}

private val TAG = "TAG"

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
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