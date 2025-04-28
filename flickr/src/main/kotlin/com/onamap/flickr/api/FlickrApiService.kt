package com.onamap.flickr.api

import com.onamap.flickr.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("services/rest/")
    suspend fun getPhotosetInfo(
        @Query("method") method: String = "flickr.photosets.getPhotos",
        @Query("api_key") apiKey: String,
        @Query("photoset_id") photosetId: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 500,
        @Query("extras") extras: String = "geo,description,date_taken,owner_name,place_url",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): FlickrResponse
} 