package com.handstandsam.app.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.handstandsam.app.services.PhotoService

fun Application.configureRouting() {
    val photoService = PhotoService()
    
    routing {
        get("/") {
            val text = "<h1>HandstandSam App</h1><br/><pre>${System.getenv().keys.joinToString("\n")}</pre>"
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
        }
        
        // Include admin routes
        configureAdminRoutes(photoService)
    }
}