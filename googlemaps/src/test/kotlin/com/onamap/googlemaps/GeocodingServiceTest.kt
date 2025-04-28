package com.onamap.googlemaps

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GeocodingServiceTest {
    // You'll need to provide your Google Maps API key here
    private val apiKey = System.getenv("GOOGLE_MAPS_API_KEY") ?: "YOUR_API_KEY_HERE"

    @Test
    fun testGetAddress() = runBlocking {
        val service = GeocodingService(apiKey)
        
        // Test with a known location (Times Square, NYC)
        val address = service.getAddress(40.7580, -73.9855)
        
        assertNotNull(address, "Address should not be null")
        assertTrue(address!!.contains("New York"), "Address should contain 'New York'")
        
        println("Address: $address")
    }

    @Test
    fun testGetAddresses() = runBlocking {
        val service = GeocodingService(apiKey)
        
        val coordinates = listOf(
            Pair(40.7580, -73.9855),  // Times Square, NYC
            Pair(51.5074, -0.1278),   // London
            Pair(48.8566, 2.3522)     // Paris
        )
        
        val addresses = service.getAddresses(coordinates)
        
        assertTrue(addresses.size == coordinates.size, "Should get addresses for all coordinates")
        
        addresses.forEach { (coord, address) ->
            println("Coordinates: $coord")
            println("Address: $address")
            println("---")
        }
    }
} 