tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    api("org.mapstruct:mapstruct:${Versions.MAPSTRUCT}")
}
