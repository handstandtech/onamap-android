package com.onamap.flickr

import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class GeocodingService(private val apiKey: String) {
    private val context = GeoApiContext.Builder()
        .apiKey(apiKey)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun getAddress(latitude: Double, longitude: Double): String? = withContext(Dispatchers.IO) {
        try {
            val results = GeocodingApi.reverseGeocode(context, com.google.maps.model.LatLng(latitude, longitude))
                .await()
            
            if (results.isNotEmpty()) {
                results[0].formattedAddress
            } else {
                null
            }
        } catch (e: Exception) {
            println("Error getting address for ($latitude, $longitude): ${e.message}")
            null
        }
    }
} 