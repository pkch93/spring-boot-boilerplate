plugins {
    kotlin("kapt") version Versions.KOTLIN
}

tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

configurations {
    compileOnly {
        extendsFrom(annotationProcessor.get())
    }
}

dependencies {
    implementation(project(":adapter:core"))
    implementation("com.infobip:infobip-spring-data-r2dbc-querydsl-boot-starter:${Versions.INFOBIP}")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    kapt("com.querydsl:querydsl-apt:${Versions.QUERYDSL}")
    kapt("org.mapstruct:mapstruct-processor:${Versions.MAPSTRUCT}")

    integrationTestImplementation(project(":test-support:testcontainers-support"))
}