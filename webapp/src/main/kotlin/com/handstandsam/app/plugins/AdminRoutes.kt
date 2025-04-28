package com.handstandsam.app.plugins

import com.handstandsam.app.services.PhotoService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import net.onamap.models.models.Photo
import java.io.File

fun Application.configureAdminRoutes(photoService: PhotoService) {
    routing {
        route("/admin") {
            // Admin home page - redirect to photos
            get {
                call.respondRedirect("/admin/photos")
            }
            
            // Get all photos page
            get("/photos") {
                val photos = photoService.getAllPhotos()
                println("Found ${photos.size} photos")
                call.respond(ThymeleafContent("admin/photos", mapOf("photos" to photos)))
            }
            
            // Get a specific photo
            get("/photos/{id}") {
                val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                val photo = photoService.getPhotoById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
                call.respond(ThymeleafContent("admin/photo-detail", mapOf("photo" to photo)))
            }
            
            // Add a new photo
            post("/photos") {
                val photo = call.receive<Photo>()
                photoService.addPhoto(photo)
                call.respond(HttpStatusCode.Created, photo)
            }
            
            // Update a photo
            put("/photos/{id}") {
                val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
                val photo = call.receive<Photo>()
                if (photo.id != id) {
                    return@put call.respond(HttpStatusCode.BadRequest)
                }
                photoService.updatePhoto(photo)
                call.respond(HttpStatusCode.OK, photo)
            }
            
            // Delete a photo
            delete("/photos/{id}") {
                val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
                photoService.deletePhoto(id)
                call.respond(HttpStatusCode.NoContent)
            }
            
            // Load photos from JSON file
            post("/photos/load") {
                val jsonFile = call.request.queryParameters["file"]?.let { File(it) }
                    ?: return@post call.respond(HttpStatusCode.BadRequest)
                photoService.loadPhotosFromJson(jsonFile)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
} 