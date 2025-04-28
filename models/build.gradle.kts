plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
}

kotlin {
  jvm {
    withSourcesJar()
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(libs.kotlinx.serialization.core)
        api(libs.kotlinx.serialization.json)
      }
    }
  }
}
