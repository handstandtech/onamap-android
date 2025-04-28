package com.handstandsam.app.services

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import net.onamap.models.models.Photo
import java.io.File

data class FlickrPhoto(
    val id: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val latitude: String?,
    val longitude: String?,
    val place_id: String?,
    val woeid: String?,
    val datetaken: String?
)

data class PhotoSet(
    val id: String,
    val primary: String,
    val owner: String,
    val ownername: String,
    val title: String,
    val photo: List<FlickrPhoto>
)

class PhotoService {
    private val photos = mutableListOf<Photo>()
    private val gson = Gson()
    
    init {
        // Load photos from the Flickr JSON file
        val jsonFile = File("/Users/samedwards/Development/onamap-android/flickr/ssaammee_albums_72157594289969939.json")
        if (jsonFile.exists()) {
            loadPhotosFromJson(jsonFile)
        }
    }
    
    fun loadPhotosFromJson(jsonFile: File) {
        val jsonString = jsonFile.readText()
        val photoset = gson.fromJson(jsonString, PhotoSet::class.java)
        photos.clear()
        photos.addAll(photoset.photo.map { flickrPhoto ->
            Photo(
                id = flickrPhoto.id,
                title = flickrPhoto.title,
                latitude = flickrPhoto.latitude,
                longitude = flickrPhoto.longitude,
                secret = flickrPhoto.secret,
                server = flickrPhoto.server,
                datetaken = flickrPhoto.datetaken?.let { 
                    try {
                        java.time.Instant.parse(it).epochSecond
                    } catch (e: Exception) {
                        0L
                    }
                } ?: 0L
            )
        })
        println("Loaded ${photos.size} photos from JSON")
    }
    
    fun getAllPhotos(): List<Photo> = photos.toList()
    
    fun getPhotoById(id: String): Photo? = photos.find { it.id == id }
    
    fun addPhoto(photo: Photo) {
        photos.add(photo)
    }
    
    fun updatePhoto(photo: Photo) {
        val index = photos.indexOfFirst { it.id == photo.id }
        if (index != -1) {
            photos[index] = photo
        }
    }
    
    fun deletePhoto(id: String) {
        photos.removeIf { it.id == id }
    }
} 