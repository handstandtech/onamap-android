package net.onamap.models.models

import java.text.SimpleDateFormat
import java.util.*

data class Photo(
    var id: String? = null,
    val url_sq: String? = null,
    val url_s: String? = null,
    val url_m: String? = null,
    val title: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val link: String? = null,
    val datetaken: Long = 0,
    val placeId: Long = 0,
    var country: String? = null,
    var region: String? = null,
    var city: String? = null,
    val secret: String? = null,
    val server: String? = null,
) {
    val lat: Double
        get() = latitude?.toDoubleOrNull() ?: 0.0
        
    val lng: Double
        get() = longitude?.toDoubleOrNull() ?: 0.0
        
    val flickrUrl: String?
        get() = if (id != null && secret != null && server != null) {
            "https://live.staticflickr.com/$server/${id}_${secret}_z.jpg"
        } else null
        
    val formattedDate: String
        get() {
            if (datetaken == 0L) return ""
            val date = Date(datetaken * 1000)
            return SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(date)
        }
}