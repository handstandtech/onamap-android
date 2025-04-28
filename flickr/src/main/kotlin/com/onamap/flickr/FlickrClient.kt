package com.onamap.flickr

import com.google.gson.GsonBuilder
import com.onamap.flickr.api.FlickrApiService
import com.onamap.flickr.model.FlickrResponse
import com.onamap.flickr.model.Photo
import com.onamap.flickr.model.Photoset
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class FlickrClient(private val apiKey: String) {
    private val api: FlickrApiService
    
    init {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
            
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            
        api = retrofit.create(FlickrApiService::class.java)
    }
    
    suspend fun getPhotosetInfo(photosetId: String): Photoset {
        val firstPage = api.getPhotosetInfo(
            method = "flickr.photosets.getPhotos",
            apiKey = apiKey,
            photosetId = photosetId,
            extras = "geo,url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o"
        )
        
        val allPhotos = mutableListOf<Photo>()
        allPhotos.addAll(firstPage.photoset.photo)
        
        val totalPages = firstPage.photoset.pages
        
        for (page in 2..totalPages) {
            val response = api.getPhotosetInfo(
                method = "flickr.photosets.getPhotos",
                apiKey = apiKey,
                photosetId = photosetId,
                extras = "geo,url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o",
                page = page
            )
            allPhotos.addAll(response.photoset.photo)
        }
        
        return firstPage.photoset.copy(photo = allPhotos)
    }

    suspend fun savePhotosetToJson(photosetId: String, outputPath: String) {
        val photoset = getPhotosetInfo(photosetId)
        val gson = GsonBuilder().setPrettyPrinting().create()
        val json = gson.toJson(photoset)
        File(outputPath).writeText(json)
    }
} 