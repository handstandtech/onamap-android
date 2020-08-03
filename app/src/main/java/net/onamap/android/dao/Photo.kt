package net.onamap.android.dao

data class Photo(
    var id: String? = null,
    val url_sq: String? = null,
    val url_s: String? = null,
    val url_m: String? = null,
    val title: String? = null,
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val link: String? = null,
    val datetaken: Long = 0,
    val placeId: Long = 0,
    var country: String? = null,
    var region: String? = null,
    var city: String? = null,
)