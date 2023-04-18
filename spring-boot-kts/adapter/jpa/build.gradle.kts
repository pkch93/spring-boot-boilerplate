plugins {
    kotlin("plugin.jpa") version Versions.KOTLIN
    kotlin("kapt") version Versions.KOTLIN
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    implementation(project(":adapter:core"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")

    integrationTestImplementation(project(":test-support:mysql"))
}