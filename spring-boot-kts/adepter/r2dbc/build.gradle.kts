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
    implementation("com.infobip:infobip-spring-data-r2dbc-querydsl-boot-starter:${Versions.INFOBIP}")
    kapt("com.querydsl:querydsl-apt:${Versions.QUERYDSL}")
    runtimeOnly("org.postgresql:r2dbc-postgresql")

    integrationTestImplementation(project(":test-support:postgresql"))
}