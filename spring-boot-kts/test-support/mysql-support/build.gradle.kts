tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    implementation(project(":test-support:testcontainers-support"))
    implementation("org.testcontainers:mysql:${Versions.TEST_CONTAINERS}")
}
