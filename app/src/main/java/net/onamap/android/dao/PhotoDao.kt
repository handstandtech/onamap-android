package net.onamap.android.dao

import android.content.Context
import java.util.*

class PhotoDao(private val context: Context) {
    fun getPhotosForState(stateName: String?): List<Photo> {
        val photosInState: MutableList<Photo> =
            ArrayList()
        val photos: List<Photo>? = JsonParser.parse(context, "handstandsam")
        photos?.forEach { photo ->
            if (photo.country.equals("United States", ignoreCase = true)) {
                if (photo.region != null && photo.region.equals(stateName, ignoreCase = true)) {
                    photosInState.add(photo)
                }
            }
        }
        return photosInState
    }
}