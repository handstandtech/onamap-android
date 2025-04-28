subprojects {
    tasks.matching { it.name.contains("lint") }.configureEach {
        enabled = false
    }

    tasks.named("check") {
        setDependsOn(dependsOn.filterNot { it.toString().contains("lint") })
    }
} 