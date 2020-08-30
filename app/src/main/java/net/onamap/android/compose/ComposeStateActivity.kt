package net.onamap.android.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
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
                UsState(state = state!!, photos = statePhotos!!)
            }
        }
    }
}
