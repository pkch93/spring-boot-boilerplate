tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    implementation(project(":test-support:testcontainers-core"))
    implementation("org.testcontainers:mysql:${Versions.TEST_CONTAINERS}")
}
