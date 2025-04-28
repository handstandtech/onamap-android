val kotlin_version="2.1.20"
val logback_version="1.4.14"
val bootstrap_version="5.3.3"

plugins {
    kotlin("jvm")
    id("io.ktor.plugin") version "3.1.2"
    // kotlin("serialization")
    libs.plugins.kotlin.serialization
}

group = "com.handstandsam.app"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-thymeleaf-jvm")
//    implementation("io.ktor:ktor-server-webjars:$ktor_version")
    implementation("org.webjars:bootstrap:$bootstrap_version")
}
