package net.onamap.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView

class UsActivity : ComponentActivity() {

//    override fun onBackPressed() {
//        if (!backPressHandler.handle()) {
//            super.onBackPressed()
//        }
//    }

//    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ComposeView(this).apply {
            setContent {
                Root.Content()
            }
        })

    }
}


