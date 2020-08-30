package net.onamap.android.dao

import android.content.Context
import java.util.*

class PhotoDao {

    companion object {

        private lateinit var handstandSamPhotos: List<Photo>

        fun init(applicationContext: Context) {
            handstandSamPhotos = JsonParser.parse(applicationContext, "handstandsam")!!
        }

        fun getPhotosForState(stateName: String): List<Photo> {
            return getPhotosForState(handstandSamPhotos, stateName)
        }

        private fun getPhotosForState(allPhotos: List<Photo>, stateName: String?): List<Photo> {
            val photosInState: MutableList<Photo> =
                ArrayList()
            allPhotos.forEach { photo ->
                if (photo.country.equals("United States", ignoreCase = true)) {
                    if (photo.region != null && photo.region.equals(stateName, ignoreCase = true)) {
                        photosInState.add(photo)
                    }
                }
            }
            return photosInState
        }
    }
}