tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    implementation("org.testcontainers:testcontainers:${Versions.TEST_CONTAINERS}")
    implementation("org.testcontainers:mysql:${Versions.TEST_CONTAINERS}")
    implementation("org.testcontainers:postgresql:${Versions.TEST_CONTAINERS}")
    implementation("org.testcontainers:junit-jupiter:${Versions.TEST_CONTAINERS}")
    implementation("org.springframework.boot:spring-boot-starter-test")
}
