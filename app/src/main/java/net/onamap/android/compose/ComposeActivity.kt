package net.onamap.android.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
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
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Row {
                            Greeting("Androi5")
                        }
                        Row {
                            Greeting("Android1")
                        }
                        Row {
                            Greeting("Android2")
                        }
                        CoilImage(data = "https://loremflickr.com/300/300")
                        imageResource(id = R.drawable.ak)
                        
                        val image = loadImageResource(id = R.drawable.ak)
                        image.resource.resource?.let {
                            Image(asset=it, modifier = Modifier.preferredSize(200.dp))
                        }
                    }
                }
            }
        }
    }
}
