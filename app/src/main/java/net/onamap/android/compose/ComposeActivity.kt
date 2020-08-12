package net.onamap.android.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.preferredHeightIn
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.preferredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import net.onamap.android.R


class ComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                UsState()
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    color = MaterialTheme.colors.primary
//                ) {
//                    Column {
//                        Row {
//                            Greeting("Androi5")
//                        }
//                        Row {
//                            Greeting("Android1")
//                        }
//                        Row {
//                            Greeting("Android2")
//                            CoilImage(
//                                data = "https://loremflickr.com/200/200",
////                            contentScale = ContentScale.FillWidth,
//                                modifier = Modifier.fillMaxSize()
//                            )
//                        }
//                        Image(imageResource(id = R.drawable.ak))
//
//                        val image = loadImageResource(id = R.drawable.ak)
//                        image.resource.resource?.let {
//                            Image(asset = it, modifier = Modifier.preferredSize(200.dp))
//                        }
//                    }
//                }
            }
        }
    }
}
