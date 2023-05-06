import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.KOTLIN
    kotlin("plugin.spring") version Versions.KOTLIN
    kotlin("plugin.jpa") version Versions.KOTLIN
    kotlin("kapt") version Versions.KOTLIN
    id("org.springframework.boot") version Versions.SPRING_BOOT
    id("io.spring.dependency-management") version Versions.SPRING_DEPENDENCY_MANAGEMENT
    id("io.gitlab.arturbosch.detekt") version Versions.DETEKT
    idea
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.toVersion(Versions.JVM)
    targetCompatibility = JavaVersion.toVersion(Versions.JVM)
}

detekt {
    toolVersion = Versions.DETEKT
    config = files("detekt.yml")
    buildUponDefaultConfig = true
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

        kotlin.srcDirs("src/integration-test/kotlin")
        resources.srcDirs("src/integration-test/resources")
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
    }
}

idea {
    module {
        testSources.from(sourceSets["integrationTest"].kotlin.srcDirs)
        testResources.from(sourceSets["integrationTest"].resources.srcDirs)
    }
}

val reportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.sarif"))
}

tasks {
    detekt {
        reports {
            sarif.required.set(true)
        }
    }
}

plugins.withType<io.gitlab.arturbosch.detekt.DetektPlugin> {
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt> detekt@{
        finalizedBy(reportMerge)

        reportMerge.configure {
            input.from(this@detekt.sarifReportFile)
        }
    }
}


dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.DETEKT}")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.github.microutils:kotlin-logging-jvm:${Versions.KOTLIN_LOGGER}")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    integrationTestImplementation("org.springframework.boot:spring-boot-starter-test")
}

