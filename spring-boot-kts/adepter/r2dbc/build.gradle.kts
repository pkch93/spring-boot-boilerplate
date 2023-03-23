tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("org.postgresql:r2dbc-postgresql")

    integrationTestImplementation(project(":test-support:postgresql"))
}