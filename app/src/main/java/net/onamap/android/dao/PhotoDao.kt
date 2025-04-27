package net.onamap.android.dao

import android.content.Context
import net.onamap.models.models.Photo

class PhotoDao {

    companion object {

        private lateinit var handstandSamPhotos: List<Photo>

        fun init(applicationContext: Context) {
            handstandSamPhotos = JsonParser.parse(applicationContext, "handstandsam")!!
            println(handstandSamPhotos)
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