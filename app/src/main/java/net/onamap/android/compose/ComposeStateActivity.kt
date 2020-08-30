package net.onamap.android.compose

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
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
import coil.api.load
import dev.chrisbanes.accompanist.coil.CoilImage
import net.onamap.android.R
import net.onamap.android.dao.Photo
import net.onamap.android.dao.PhotoDao
import net.onamap.android.model.StateData
import net.onamap.android.model.States


class ComposeStateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val stateData = intent.getSerializableExtra("state") as StateData?
        val stateFullName = stateData?.fullName ?: "Alaska"
        val state = States.states.find { it.fullName == stateFullName }

        val statePhotos: List<Photo>? = PhotoDao(applicationContext).getPhotosForState(stateFullName)
        setContent {
            MyApplicationTheme {
                UsState(state!!, statePhotos!!)
            }
        }
    }
}
