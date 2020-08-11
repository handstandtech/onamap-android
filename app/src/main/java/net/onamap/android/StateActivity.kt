package net.onamap.android

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import coil.api.load
import net.onamap.android.dao.Photo
import net.onamap.android.dao.PhotoDao
import net.onamap.android.model.StateData

class StateActivity : BaseActivity() {
    private val imageView by lazy {
        this.findViewById<View>(R.id.imageView) as AppCompatImageView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state)
        val stateData = intent.getSerializableExtra("state") as StateData
        supportActionBar!!.title = stateData.abbv
        val textView = findViewById<View>(R.id.text) as TextView
        imageView.setImageResource(stateData.icon)
        textView.text = stateData.fullName

        val statePhotos: List<Photo>? = PhotoDao(applicationContext).getPhotosForState(stateData.fullName)
        val firstStatePhoto = statePhotos?.get(0)
        if (statePhotos != null) {
            val photoView = findViewById<View>(R.id.photoView) as AppCompatImageView
            photoView.load(firstStatePhoto?.url_m)
        } else {
            Toast.makeText(this, "No Photos in This State", Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }
}