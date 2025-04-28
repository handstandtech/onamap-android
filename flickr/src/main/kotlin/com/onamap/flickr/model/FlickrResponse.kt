package com.onamap.flickr.model

import com.google.gson.annotations.SerializedName

data class FlickrResponse(
    val photoset: Photoset,
    val stat: String
)

data class Photoset(
    val id: String,
    val primary: String,
    val owner: String,
    val ownername: String,
    val photo: List<Photo>,
    val page: Int,
    val per_page: Int,
    val perpage: Int,
    val pages: Int,
    val total: Int,
    val title: String
)

data class Photo(
    val id: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val isprimary: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int,
    val latitude: String?,
    val longitude: String?,
    val accuracy: String?,
    val context: Int?,
    val place_id: String?,
    val woeid: String?,
    val geo_is_public: Int?,
    val geo_is_contact: Int?,
    val geo_is_friend: Int?,
    val geo_is_family: Int?
) 