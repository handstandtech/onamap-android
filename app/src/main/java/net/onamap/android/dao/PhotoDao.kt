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
            return allPhotos.filter {
                it.country == "United States" && it.region == stateName
            }
        }
    }
}