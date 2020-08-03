package net.onamap.android

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import net.onamap.android.dao.Photo
import net.onamap.android.dao.PhotoDao
import net.onamap.android.model.StateData

class StateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state)
        val stateData = intent.getSerializableExtra("state") as StateData
        supportActionBar!!.title = stateData.abbv
        val imageView = findViewById<View>(R.id.imageView) as AppCompatImageView
        val textView = findViewById<View>(R.id.text) as TextView
        imageView.setImageResource(stateData.icon)
        textView.text = stateData.fullName
        val statePhotos: List<Photo>? = PhotoDao(applicationContext).getPhotosForState(stateData.fullName)
        if (statePhotos != null) {
            val photoView = findViewById<View>(R.id.photoView) as AppCompatImageView
            Picasso.with(applicationContext).load(statePhotos[0].url_m).into(photoView)
        } else {
            Toast.makeText(this, "No Photos in This State", Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }
}