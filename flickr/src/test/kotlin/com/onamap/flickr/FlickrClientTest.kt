package com.onamap.flickr

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.File
import kotlin.test.assertTrue

class FlickrClientTest {
    private val apiKey = "dabe41fb2e4ba5ced63ebe55a4c9d753"
    private val photosetId = "72157594289969939"

    @Test
    fun testGetPhotosetInfo() = runBlocking {
        val client = FlickrClient(apiKey)
        val photoset = client.getPhotosetInfo(photosetId)
        
        println("Photoset Title: ${photoset.title}")
        println("Total Photos: ${photoset.total}")
        println("Photos with Geo Data: ${photoset.photo.count { it.latitude != null && it.longitude != null }}")
        
        // Print first few photos with geo data
        photoset.photo
            .filter { it.latitude != null && it.longitude != null }
            .take(5)
            .forEach { photo ->
                println("Photo: ${photo.title}")
                println("  Lat: ${photo.latitude}, Long: ${photo.longitude}")
            }
    }

    @Test
    fun testSavePhotosetToJson() = runBlocking {
        val client = FlickrClient(apiKey)
        val outputPath = "ssaammee_albums_${photosetId}.json"
        
        client.savePhotosetToJson(photosetId, outputPath)
        
        val savedFile = File(outputPath)
        assertTrue(savedFile.exists(), "JSON file should be created")
        assertTrue(savedFile.length() > 0, "JSON file should not be empty")
        
        // Print the file location for easy access
        println("JSON file saved to: ${savedFile.absolutePath}")
        
        // Print first few lines of the JSON file to verify content
        println("\nFirst few lines of the JSON file:")
        savedFile.readLines().take(10).forEach { println(it) }
    }
} 