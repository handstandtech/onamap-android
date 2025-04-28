package com.onamap.googlemaps

import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class GeocodingService(private val apiKey: String) {
    private val context = GeoApiContext.Builder()
        .apiKey(apiKey)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun getAddress(latitude: Double, longitude: Double, retryCount: Int = 3): String? = withContext(Dispatchers.IO) {
        var attempts = 0
        while (attempts < retryCount) {
            try {
                val results = GeocodingApi.reverseGeocode(context, com.google.maps.model.LatLng(latitude, longitude))
                    .await()
                
                if (results.isNotEmpty()) {
                    return@withContext results[0].formattedAddress
                }
                return@withContext null
            } catch (e: Exception) {
                attempts++
                if (attempts >= retryCount) {
                    println("Error getting address for ($latitude, $longitude) after $retryCount attempts: ${e.message}")
                    return@withContext null
                }
                // Wait before retrying (exponential backoff)
                delay(1000L * (1 shl attempts))
            }
        }
        null
    }

    suspend fun getAddresses(coordinates: List<Pair<Double, Double>>): Map<Pair<Double, Double>, String?> {
        return coordinates.associateWith { (lat, lng) ->
            getAddress(lat, lng)
        }
    }
} 