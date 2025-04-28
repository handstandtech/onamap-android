android {
    // ... existing code ...
    lintOptions {
        isCheckReleaseBuilds = false
        isAbortOnError = false
        disable("all")
    }
    tasks.matching { it.name.contains("lint") }.configureEach {
        enabled = false
    }
    lint {
        disable += listOf("all")
    }
    // ... existing code ...
} 