import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.KOTLIN
    kotlin("plugin.spring") version Versions.KOTLIN apply false
    kotlin("plugin.jpa") version Versions.KOTLIN apply false
    kotlin("kapt") version Versions.KOTLIN apply false
    kotlin("plugin.allopen") version Versions.KOTLIN apply false
    kotlin("plugin.noarg") version Versions.KOTLIN apply false
    id("org.springframework.boot") version Versions.SPRING_BOOT apply false
    id("io.spring.dependency-management") version Versions.SPRING_DEPENDENCY_MANAGEMENT apply false
    idea
}

java {
    sourceCompatibility = JavaVersion.toVersion(Versions.JVM)
    targetCompatibility = JavaVersion.toVersion(Versions.JVM)
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("java-library")
        plugin("java-test-fixtures")

        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("idea")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = Versions.JVM
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    sourceSets {
        create("integrationTest") {
            compileClasspath += sourceSets.main.get().output
            runtimeClasspath += sourceSets.main.get().output

            kotlin.srcDir("src/integration-test/kotlin")
            resources.srcDir("src/integration-test/resources")
        }
    }


    val integrationTestImplementation by configurations.getting {
        extendsFrom(configurations.implementation.get())
    }
    val integrationTestRuntimeOnly by configurations.getting {
        extendsFrom(configurations.runtimeOnly.get())
    }

    tasks {
        create("integrationTest", Test::class) {
            group = "verification"
            testClassesDirs = sourceSets["integrationTest"].output.classesDirs
            classpath = sourceSets["integrationTest"].runtimeClasspath

            reports {
                ignoreFailures = true
            }
        }
    }

    idea {
        module {
            testSources.from(sourceSets["integrationTest"].kotlin.srcDirs)
            testResources.from(sourceSets["integrationTest"].resources.srcDirs)
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("io.github.microutils:kotlin-logging-jvm:${Versions.KOTLIN_LOGGER}")

        implementation("org.springframework.boot:spring-boot-starter")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        integrationTestImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}
