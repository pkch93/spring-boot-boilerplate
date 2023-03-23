tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")

    integrationTestImplementation(project(":test-support:mysql"))
}